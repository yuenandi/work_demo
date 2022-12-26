package org.example.annotation.demo;

import org.example.annotation.demo.annotations.SimpleInject;
import org.example.annotation.demo.annotations.SimpleSingleton;

@SimpleSingleton
public class ServiceA {
    @SimpleInject
    ServiceB b;
    public void callB(){
        b.action();
    }
}
