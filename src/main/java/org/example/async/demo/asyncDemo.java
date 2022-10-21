package org.example.async.demo;

import com.sun.javafx.collections.MappingChange;

import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.Random;
import java.util.concurrent.*;

public class asyncDemo {
    private static ExecutorService executorService = Executors.newFixedThreadPool(20);

    public static void main(String[] args) throws InterruptedException {
        String test =  "{\"groupName\":\"afaProxy\",\"resource\":{\"name\":\"afaProxy.aar\"},\"restart\":\"false\",\"pods\":[{\"podIp\":\"10.0.2.123\",\"podId\":\"afaproxy-v1-88cc87b8f-4kptz\"}],\"autostart\":\"false\"}";
        JSON testJson = JSON.parseObject(test);
        System.out.println(testJson);
//        for (int i = 0; i < 3; i++) {
//            new Thread(() -> {
//                try {
//                    asyncDemo1();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }
    }

    public static void asyncDemo1() throws ExecutionException, InterruptedException {
            executorService.submit((Callable<Object>) () -> {
                System.out.println(Thread.currentThread().getName() + "===task start=== 等待5s");
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + "===task finish=== 结束5s");
                return new Random().nextInt();
            });
            System.out.println("==============");
            executorService.submit((Callable<Object>) () -> {
                System.out.println(Thread.currentThread().getName() + "===task start=== 等待3s");
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + "===task finish=== 结束3s");
                return new Random().nextInt();
            });
        CompletableFuture.runAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "===task start=== 等待10s");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "===task finish=== 结束10s");
        }, executorService);
        executorService.shutdown();
    }
}
