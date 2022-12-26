package org.example.proxy.demo.simpleAOP;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.example.annotation.demo.annotations.SimpleInject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class CGLibContainer {
    static Map<Class<?>, Map<InterceptPoint, List<Method>>> interceptMethodMap = new HashMap<>();

    static Class<?>[] aspects = new Class<?>[]{ServiceLogAspect.class, ExceptionAspect.class};

    static {
        init();
    }

    public static <T> T getInstance(Class<?> cls){
        try {
            T obj = createInstance(cls);
            Field[] fields = cls.getDeclaredFields();
            for(Field field: fields){
                if(field.isAnnotationPresent(SimpleInject.class)){
                    if(!field.isAccessible()){
                        field.setAccessible(true);
                    }
                    Class<?> fieldCls = field.getType();
                    field.set(obj, getInstance(fieldCls));
                }
            }
            return obj;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private static void init() {
        for (Class<?> cls : aspects) {
            Aspect aspect = cls.getAnnotation(Aspect.class);
            if (aspect != null) {
                Method before = getMethod(cls, "before", new Class<?>[]{Object.class,Method.class, Object[].class});
                Method after = getMethod(cls, "after", new Class<?>[]{Object.class,Method.class, Object[].class, Object.class});
                Method exception = getMethod(cls, "exception", new Class<?>[]{Object.class,Method.class, Object[].class, Throwable.class});
                Class<?>[] intercepttedArr = aspect.value();
                for(Class<?> interceptted : intercepttedArr){
                    addInterceptMethod(interceptted, InterceptPoint.BEFORE, before);
                    addInterceptMethod(interceptted, InterceptPoint.AFTER, after);
                    addInterceptMethod(interceptted, InterceptPoint.EXCEPTION, exception);
                }
            }
        }
    }

    public static Method getMethod(Class<?> cls,String methodName, Class<?>[] args ){
        try {
            if((cls == ServiceLogAspect.class && (methodName == "before" || methodName == "after")) || (cls == ExceptionAspect.class && methodName == "exception" ) ){
                return cls.getMethod(methodName, args);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void addInterceptMethod(Class<?> cls, InterceptPoint point, Method method){
        if( method == null){
            return;
        }
        Map<InterceptPoint, List<Method>> map = interceptMethodMap.get(cls);

        if (map == null ){
            map = new HashMap<>();
            interceptMethodMap.put(cls, map);
        }

        List<Method> methods = map.get(point);
        if (methods == null){
            methods = new ArrayList<>();
            map.put(point, methods);
        }
        methods.add(method);
    }

    static class AspectInterceptor implements MethodInterceptor {

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

            List<Method> beforeMethods = getInterceptMethods(o.getClass().getSuperclass(), InterceptPoint.BEFORE);
            for (Method m: beforeMethods){
                m.invoke(null, new Object[]{o, method, objects});
            }
            try {
                Object result = methodProxy.invokeSuper(o, objects);

                List<Method> afterMethods = getInterceptMethods(o.getClass().getSuperclass(), InterceptPoint.AFTER);
                for(Method m: afterMethods){
                    m.invoke(null, new Object[]{o, method, objects, result});
                }
                return result;
            }catch (Throwable e){
                List<Method> exceptionMethods = getInterceptMethods(o.getClass().getSuperclass(), InterceptPoint.EXCEPTION);
                for(Method m: exceptionMethods){
                    m.invoke(null, new Object[]{o, method, objects, e});
                }
                throw e;
            }
        }
    }

    static List<Method> getInterceptMethods(Class<?> cls, InterceptPoint point){
        Map<InterceptPoint, List<Method>> map = interceptMethodMap.get(cls);
        if(map == null){
            return Collections.emptyList();
        }
        List<Method> methods = map.get(point);
        if(methods == null) {
            return Collections.emptyList();
        }
        return methods;
    }

    private static <T> T createInstance(Class<?> cls) throws IllegalAccessException, InstantiationException {
        if (!interceptMethodMap.containsKey(cls)){
            return (T) cls.newInstance();
        }
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cls);
        enhancer.setCallback(new AspectInterceptor());
        return (T) enhancer.create();
    }
}
