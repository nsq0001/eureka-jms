package com.eureka.jms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @author nsq
 * @Title:
 * @Package
 * @Description:
 * @date 2021-06-2311:12
 */
@RestController
@RequestMapping("/producer")
public class ProducerController {

	@Autowired
	Queue queue;

	@Autowired
	Topic topic;

	//注入springboot封装的工具类
	@Autowired
	private JmsMessagingTemplate jms;


	/**
	 * 点对点模式（queue）模式发消息
	 * @param text
	 */
	@GetMapping("/queue-send")
	@ResponseBody
	public String queueSend(String text) {
		//发送消息至消息中间件代理（Broker）
		jms.convertAndSend(queue, text);

		return "success";
	}

	/**
	 * 订阅模式（topic）发送消息
	 * @param text
	 * @return
	 */
	@RequestMapping("/topic-send")
	public String topicSend(String text){
		jms.convertAndSend(topic,text);
		return "topic 发送成功";
	}

}
