package org.example.designPatterns.demo.builder;

import lombok.Data;
import org.example.designPatterns.demo.abstractFactory.MailSender;
import org.example.designPatterns.demo.abstractFactory.Sender;
import org.example.designPatterns.demo.abstractFactory.SmsSender;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuenandi
 * @description 工厂模式关注的是创建单个产品，而建造者模式则关注创建符合对象，多个部分
 * @date 2020/12/3
 */
@Data
public class Builder {

    private List<Sender> list = new ArrayList<Sender>();
    public void produceMailSender(int count){
        for (int i = 0; i < count; i++) {
            list.add(new MailSender());
        }
    }
    public void produceSmsSender(int count){
        for (int i = 0; i < count; i++) {
            list.add(new SmsSender());
        }
    }
}
