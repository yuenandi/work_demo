package org.example.async.demo.sampleCP;

public class SampleConsumer extends Thread{
    MyBlockingQueue<String> queue;
    public SampleConsumer(MyBlockingQueue<String> queue){
        this.queue = queue;
    }
    @Override
    public void run(){
        while (true){
            String task = null;
            try {
                task = queue.take();
                System.out.println("handle task" + task);
                Thread.sleep((int)(Math.random() * 1000 ));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
