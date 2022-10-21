package org.example.designPatterns.demo.factoryMethod;

/**
 * @author yuenandi
 * @description
 * @date 2020/12/3
 */
public class Test {
    public static void main(String[] args) {
//        SendFactory sendFactory = new SendFactory();
//        Sender sender = sendFactory.produce("sms");
//        Sender sender = sendFactory.produceMail();
        Sender sender = SendFactory.produceMail();
        sender.Send();
    }
}