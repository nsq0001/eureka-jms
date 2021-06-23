package com.eureka.jms.service;

import javax.jms.Destination;

/** 
 * @description 
 * @author nsq
 * @date 2021-06-23 11:13
 * @param null
 * @return 
 */
public interface ProduceService {

    /**
     * 向指定队列发送消息
     * @param destination
     * @param message
     */
    public void send(Destination destination , String message);
}
