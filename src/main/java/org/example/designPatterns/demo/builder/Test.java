package org.example.designPatterns.demo.builder;

/**
 * @author yuenandi
 * @description 工厂模式测试
 * @date 2020/12/3
 */
public class Test {
    public static void main(String[] args) {
        Builder builder = new Builder();
        builder.produceMailSender(10);
    }
}
