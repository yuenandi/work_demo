package org.example.proxy.demo.simpleAOP;


import org.example.annotation.demo.ServiceB;


import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect(ServiceB.class)
public class ExceptionAspect {
    public static void exception(Object obj, Method method, Object[] args, Throwable e){
        System.out.println("exception when calling: " + method.getName() + "," + Arrays.toString(args));

    }

}
