package com.eureka.jms.component;

import org.springframework.stereotype.Component;

/**
 * @author nsq
 * @Title:
 * @Package
 * @Description:
 * @date 2021-06-2216:04
 */
@Component
public class ConsumenTopic {

	/**
	 * 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
	 * @param text
	 */
//	@JmsListener(destination = "mqTopic",containerFactory = "jmsListenerContainerTopic")
	public void receiveTopic1(String text) {
		System.out.println("消息消費者收到的Topic1报文为:" + text);
	}

//	@JmsListener(destination = "mqTopic",containerFactory = "jmsListenerContainerTopic")
	public void receiveTopic2(String text) {
		System.out.println("消息消費者收到的Topic2报文为:" + text);
	}
}
