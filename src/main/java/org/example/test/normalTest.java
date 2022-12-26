package org.example.test;

import java.util.ArrayList;
import java.util.List;


public class normalTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i<=10; i++){
            list.add("test");
        }
        System.out.printf("++++++++++" + list.toString());
    }
}
