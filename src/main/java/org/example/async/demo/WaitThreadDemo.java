package org.example.async.demo;

public class WaitThreadDemo extends Thread{
    private volatile boolean fire = false;

    public static void main(String[] args) throws InterruptedException {
        WaitThreadDemo waitThreadDemo = new WaitThreadDemo();
        waitThreadDemo.start();
        Thread.sleep(10000);
        System.out.println("fire");
        waitThreadDemo.fire();
    }

    @Override
    public void run(){
        synchronized (this){
            while (!fire){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("fired");
        }
    }

    public synchronized void fire(){
        this.fire = true;
        notify();
    }

}
