package com.nisq.mq.netty;

import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @description
 * @author nsq
 * @return
 */
public class ThreadMQ {

    private static LinkedBlockingDeque<JSONObject> msgs = new LinkedBlockingDeque<JSONObject>();

    public static void main(String[] args) {
        // 生产线程
        Thread producerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(1000);
                        JSONObject data = new JSONObject();
                        data.put("userId", "1234");
                        // 存入消息
                        msgs.offer(data);
                    }
                } catch (Exception e) {

                }

            }
        }, "生产者");
        producerThread.start();
        // 消费者线程
        Thread consumerThread  = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        JSONObject data = msgs.poll();
                        if (data != null) {
                            System.out.println(Thread.currentThread().getName() + "," + data);
                        }
                    }

                } catch (Exception e) {

                }

            }
        }, "消费者");
        consumerThread.start();
    }
}
