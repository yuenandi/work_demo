package org.example.reflection.demo;

import lombok.Data;
import org.example.utils.NameData;
import org.example.utils.Utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;

@Data
public class MyClass extends NameData {

    public final int num;
    private int age;
    String sex;

    public MyClass(int num, int age, String sex) {
        super();
        this.num = num;
        this.age = age;
        this.sex = sex;

    }

    public static void main(String[] args) {


        MyClass myClass = new MyClass(01, 12, "girl");
        NameData nameData = new MyClass(01, 12, "girl");


        try {
            Class<?> ndCls = Class.forName("org.example.utils.NameData");

            Class<?> myCls = MyClass.class;

            /**
             * 类名称信息
             */
            Utils.MySystemOutPf("类名称:", 1);
            Utils.MySystemOutPf("getName: " + ndCls.getName(), 2);
            Utils.MySystemOutPf("getSimpleName: " +ndCls.getSimpleName(), 2);
            Utils.MySystemOutPf("getCanonicalName: " + ndCls.getCanonicalName(), 2);
            Utils.MySystemOutPf("getPackage: " +ndCls.getPackage().getName(), 3);

            /**
             * 字段信息
             */
            Utils.MySystemOutPf("所有的public字段，包括父类，无字段返回空",1);
            Arrays.asList(myCls.getFields()).forEach(field -> {
                Utils.MySystemOutPf(field.toString(),2);
            });
            Utils.MySystemOutPf("",0);

            Utils.MySystemOutPf("所有字段，不包括父类 myClass：", 1);
            Arrays.asList(myCls.getDeclaredFields()).forEach(field -> {
                Utils.MySystemOutPf(field.toString(),2);
            });
            Utils.MySystemOutPf("",0);
            Utils.MySystemOutPf("所有字段，不包括父类 nameData: ",1);
            Arrays.asList(ndCls.getDeclaredFields()).forEach(ndField -> {
                Utils.MySystemOutPf(ndField.toString(),2);

                //获取字段名称
                Utils.MySystemOutPf("字段名称(getName): " + ndField.getName(), 2);
                //返回字段修饰符
                Utils.MySystemOutPf("字段修饰符(getModifiers): " + Modifier.toString(ndField.getModifiers()),2);
                Utils.MySystemOutPf("isFinal: " + Modifier.isFinal(ndField.getModifiers()),2);
                //返回字段类型
                Utils.MySystemOutPf("字段类型(getType): " + ndField.getType().getSimpleName(),2);

                //判断是否有访问字段权限
                if (!ndField.isAccessible()) {
                    ndField.setAccessible(true);
                }
                try {
                    //将指定对象obj中的该字段的值设置为value
                    switch (ndField.getName()) {
                        case "name":
                            ndField.set(nameData, "ynd");
                            break;
                        case "idCardNumber":
                            ndField.set(nameData, "2323301122333454532332");
                            break;
                        case "friend":
                            ndField.set(nameData, "liangLiang");
                            break;
                    }

                    //获取指定对象obj中该字段的值
                    Utils.MySystemOutPf("对象值(get):" + ndField.get(nameData),3);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });

            System.out.printf("本类中或父类中指定名称的public字段：\n%s\n", myCls.getField("name"));
            System.out.printf("本类中或父类中指定名称的public字段：\n%s\n", myCls.getField("num"));

            System.out.printf("本类中声明的字段：\n%s\n", myCls.getDeclaredField("num"));


            /**
             * 方法信息
             */

            Utils.MySystemOutPf("方法反射: " + ndCls.getMethod("getName").invoke(nameData), 1);
            Utils.MySystemOutPf("",0);

            /**
             * 构建对象和构造方法
             */
            Object ndClass1 = NameData.class.newInstance();

            Constructor<?> constructor = ndCls.getConstructor(String.class, String.class);
            Object ndClass2 = constructor.newInstance("ddd", "ddd");
            System.out.println(ndCls.getMethod("getName").invoke(ndClass2));


        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


    }



}
