package org.example.designPatterns.demo.factoryMethod;

/**
 * @author yuenandi
 * @description
 * @date 2020/12/3
 */
public class SmsSender implements Sender {
    @Override
    public void Send() {
        System.out.println("This is a SmsSender");
    }
}
