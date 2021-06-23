package com.eureka.jms.component;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @author nsq
 * @Title:
 * @Package
 * @Description:
 * @date 2021-06-2216:04
 */
@Component
public class ConsumenQueue {

	@JmsListener(destination = "ActiveMQQueue" )
	public void revQueue(TextMessage textMessage, Session session){

		try {
			System.out.println("消费者受到的消息为："+textMessage.getText());

			textMessage.acknowledge();
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
}
