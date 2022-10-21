package org.example.designPatterns.demo.abstractFactory;

/**
 * @author yuenandi
 * @description
 * @date 2020/12/3
 */
public class MailSenderFactory implements Provider {
    @Override
    public Sender produce() {
        return new MailSender();
    }
}
