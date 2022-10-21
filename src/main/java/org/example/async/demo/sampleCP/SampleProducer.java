package org.example.async.demo.sampleCP;

public class SampleProducer extends Thread{
    MyBlockingQueue<String> queue;
    public SampleProducer(MyBlockingQueue<String> queue){
        this.queue = queue;
    }
    @Override
    public void run(){
        int num = 0;
        try{
            while (true){
                String task = String.valueOf(num);
                queue.put(task);
                System.out.println("produce task" + task);
                num++;
                Thread.sleep((int)(Math.random() * 1000 ));
            }
        }catch (InterruptedException e){

        }

    }
}
