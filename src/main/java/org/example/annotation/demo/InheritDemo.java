package org.example.annotation.demo;


import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *  Test��һ��ע�⣬��Base�и�ע�⣬Child�̳���Base����û������ע��
 *  main�������Child���Ƿ���Testע�⣬���Ϊtrue��������ΪTest��ע��@Interited
 *  ���ȥ�����Ϊfalse
 */
public class InheritDemo {
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    static @interface Test{

    }
    @Test
    static class Base {

    }
    static class Child extends Base {

    }

    public static void main(String[] args) {
        System.out.println(Child.class.isAnnotationPresent(Test.class));
    }

}
