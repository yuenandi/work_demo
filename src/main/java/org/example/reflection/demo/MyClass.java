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
             * ��������Ϣ
             */
            Utils.MySystemOutPf("������:", 1);
            Utils.MySystemOutPf("getName: " + ndCls.getName(), 2);
            Utils.MySystemOutPf("getSimpleName: " +ndCls.getSimpleName(), 2);
            Utils.MySystemOutPf("getCanonicalName: " + ndCls.getCanonicalName(), 2);
            Utils.MySystemOutPf("getPackage: " +ndCls.getPackage().getName(), 3);

            /**
             * �ֶ���Ϣ
             */
            Utils.MySystemOutPf("���е�public�ֶΣ��������࣬���ֶη��ؿ�",1);
            Arrays.asList(myCls.getFields()).forEach(field -> {
                Utils.MySystemOutPf(field.toString(),2);
            });
            Utils.MySystemOutPf("",0);

            Utils.MySystemOutPf("�����ֶΣ����������� myClass��", 1);
            Arrays.asList(myCls.getDeclaredFields()).forEach(field -> {
                Utils.MySystemOutPf(field.toString(),2);
            });
            Utils.MySystemOutPf("",0);
            Utils.MySystemOutPf("�����ֶΣ����������� nameData: ",1);
            Arrays.asList(ndCls.getDeclaredFields()).forEach(ndField -> {
                Utils.MySystemOutPf(ndField.toString(),2);

                //��ȡ�ֶ�����
                Utils.MySystemOutPf("�ֶ�����(getName): " + ndField.getName(), 2);
                //�����ֶ����η�
                Utils.MySystemOutPf("�ֶ����η�(getModifiers): " + Modifier.toString(ndField.getModifiers()),2);
                Utils.MySystemOutPf("isFinal: " + Modifier.isFinal(ndField.getModifiers()),2);
                //�����ֶ�����
                Utils.MySystemOutPf("�ֶ�����(getType): " + ndField.getType().getSimpleName(),2);

                //�ж��Ƿ��з����ֶ�Ȩ��
                if (!ndField.isAccessible()) {
                    ndField.setAccessible(true);
                }
                try {
                    //��ָ������obj�еĸ��ֶε�ֵ����Ϊvalue
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

                    //��ȡָ������obj�и��ֶε�ֵ
                    Utils.MySystemOutPf("����ֵ(get):" + ndField.get(nameData),3);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });

            System.out.printf("�����л�����ָ�����Ƶ�public�ֶΣ�\n%s\n", myCls.getField("name"));
            System.out.printf("�����л�����ָ�����Ƶ�public�ֶΣ�\n%s\n", myCls.getField("num"));

            System.out.printf("�������������ֶΣ�\n%s\n", myCls.getDeclaredField("num"));


            /**
             * ������Ϣ
             */

            Utils.MySystemOutPf("��������: " + ndCls.getMethod("getName").invoke(nameData), 1);
            Utils.MySystemOutPf("",0);

            /**
             * ��������͹��췽��
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
