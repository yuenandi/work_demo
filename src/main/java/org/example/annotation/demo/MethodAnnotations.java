package org.example.annotation.demo;

import java.lang.annotation.*;
import java.lang.reflect.Method;

public class MethodAnnotations {
    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    static @interface QueryParam {
        String value();
    }

    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    static @interface DefaultValue{
        String value() default "";
    }

    public void hello(@QueryParam("action") String action, @QueryParam("sort") @DefaultValue("asc") String sort) {

    }

    public static void main(String[] args) throws NoSuchMethodException {
        Class<?> cls = MethodAnnotations.class;
        Method method = cls.getMethod("hello", String.class, String.class);
        Annotation[][] annotations = method.getParameterAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            System.out.println("Annotations for paramter "+ (i+1));
            Annotation[] annotationsArr = annotations[i];
            for (Annotation annotation:annotationsArr) {
                if( annotation instanceof QueryParam){
                    QueryParam qp = (QueryParam) annotation;
                    System.out.println(qp.annotationType().getSimpleName() + ":" + qp.value());
                }else if (annotation instanceof DefaultValue){
                    DefaultValue dv = (DefaultValue) annotation;
                    System.out.println(dv.annotationType().getSimpleName() + ":" + dv.value());
                }

            }

        }
    }

}
