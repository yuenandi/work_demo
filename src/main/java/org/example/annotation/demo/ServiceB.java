package org.example.annotation.demo;


import org.example.annotation.demo.annotations.SimpleSingleton;

@SimpleSingleton
public class ServiceB {
    public void action(){
        System.out.println("I am B: ");
    }
}
