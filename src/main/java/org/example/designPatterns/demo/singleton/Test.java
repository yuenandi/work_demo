package org.example.designPatterns.demo.singleton;

/**
 * @author yuenandi
 * @description
 * @date 2020/12/3
 */
public class Test {
    public static void main(String[] args) {
        Singleton1 si = Singleton1.getInstance();
        System.out.println(si);
        System.out.println(si.readResolve());

        Singleton2 s2 = Singleton2.getInstance();
        System.out.println(s2);
        System.out.println(s2.readResolve());
    }
}
