package org.example.designPatterns.demo.factoryMethod;

/**
 * @author yuenandi
 * @description
 * @date 2020/12/3
 */
public class SendFactory {
    //普通工厂模式
    public Sender produce(String type) {
        if (type.equals("mail")) {
            return new MailSender();
        } else if (type.equals("sms")) {
            return new SmsSender();
        }else {
            System.out.println("请输入正确的类型");
            return null;
        }
    }

//    public Sender produceMail(){
//        return new MailSender();
//    }
//    public Sender produceSms(){
//        return new SmsSender();
//    }
    public static Sender produceMail(){
        return new MailSender();
    }
    public static Sender porduceSms(){
        return new SmsSender();
    }

}
