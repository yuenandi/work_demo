package org.example.designPatterns.demo.abstractFactory;

/**
 * @author yuenandi
 * @description 抽象工厂测试
 * @date 2020/12/3
 */
public class Test {
    public static void main(String[] args) {
        Provider provider = new SmsSenderFactory();
        Sender sender = provider.produce();
        sender.Send();
    }
}
