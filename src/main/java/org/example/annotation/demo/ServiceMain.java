package org.example.annotation.demo;

import org.example.annotation.demo.annotations.utils;
import org.example.proxy.demo.simpleAOP.CGLibContainer;

public class ServiceMain {
    public static void main(String[] args) {
        ServiceA a = utils.getInstance(ServiceA.class);
        System.out.println("I am a: " + a);
        a.callB();

        ServiceA a1 = utils.getInstance(ServiceA.class);
        System.out.println("I am a1: " + a1);
        a1.callB();


        ServiceA as = utils.getSingletonInstance(ServiceA.class);
        System.out.println("I am as: " + as);
        as.callB();

        ServiceA as1 = utils.getSingletonInstance(ServiceA.class);
        System.out.println("I am as1: " + as1);
        as1.callB();

        ServiceA aAspect = CGLibContainer.getInstance(ServiceA.class);
        aAspect.callB();
    }
}
