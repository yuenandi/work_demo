package org.example.designPatterns.demo.prototype;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person person1 = new Person();
        person1.setName("liang");
        person1.setAge(29);
        Person person2 = (Person) person1.deepClone();
        person2.setAge(28);
        System.out.printf("%s: %s\n",person1.getName(), person1.getAge());
        System.out.printf("%s: %s\n",person2.getName(), person2.getAge());
    }
}
