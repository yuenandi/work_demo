package org.example.designPatterns.demo.prototype;

import lombok.Data;

import java.io.*;

@Data
public class Person implements Serializable {
    private static final long serialVersionUID = -6293020566235978787L;
    private String name;
    private Integer age;

    /* 浅复制 */
    public Object clone() throws CloneNotSupportedException {
        Person proto = (Person) super.clone();
        return proto;
    }

    /* 深复制 */
    public Object deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }
}
