package org.example.designPatterns.demo.abstractFactory;

/**
 * @author yuenandi
 * @description
 * @date 2020/12/3
 */
public class SmsSender implements Sender {
    @Override
    public void Send() {
        System.out.println("This is a smsSender");
    }
}
