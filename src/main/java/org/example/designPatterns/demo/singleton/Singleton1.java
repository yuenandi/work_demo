package org.example.designPatterns.demo.singleton;

/**
 * @author yuenandi
 * @description
 * @date 2020/12/3
 */
public class Singleton1 {
    /* 私有构造方法，防止被实例化 */
    private static Singleton1 instance = null;
    /* 私有构造方法，防止被实例化 */
    private Singleton1(){
    }
    /* 静态工程方法，创建实例 */
    public static Singleton1 getInstance(){
        if(instance == null){
            synchronized (Singleton1.class){
                if (instance == null){
                    instance = new Singleton1();
                    return instance;
                }
            }
        }
        return instance;
    }
    /* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
    public Object readResolve(){
        return instance;
    }
}
