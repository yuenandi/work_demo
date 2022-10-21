package org.example.async.demo.sampleCP;

public class test {
    public static void main(String[] args) {
        MyBlockingQueue<String> queue = new MyBlockingQueue<>(10);
        new SampleProducer(queue).start();
        new SampleConsumer(queue).start();
    }
}
