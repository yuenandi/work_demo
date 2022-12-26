package org.example.proxy.demo.dynamicProxy;

import java.lang.reflect.*;

public class SimpleJDKDynamicProxyDemo {
    static interface IService{
        public void sayHello();
    }

    static class RealService implements IService {
        @Override
        public void sayHello() {
            System.out.println("hello");

        }
    }
    static class SimpleInvocationHandler implements InvocationHandler{
        private Object realObj;
        public SimpleInvocationHandler(Object realObj){
            this.realObj = realObj;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("entering " + method.getName());
            Object result = method.invoke(realObj, args);
            System.out.println("leaving " + method.getName());
            return result;
        }
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        IService realService = new RealService();
        //newProxyInstance用法
        //IService proxyService = (IService) Proxy.newProxyInstance(IService.class.getClassLoader(), new Class<?>[]{IService.class}, new SimpleInvocationHandler(realService));

        // getProxyClass 用法
        Class<?> proxyCls = Proxy.getProxyClass(IService.class.getClassLoader(), new Class<?>[]{IService.class});
        Constructor ctor = proxyCls.getConstructor(InvocationHandler.class);
        InvocationHandler handler = new SimpleInvocationHandler(realService);
        IService proxyService = (IService) ctor.newInstance(handler);

        proxyService.sayHello();
    }

}
