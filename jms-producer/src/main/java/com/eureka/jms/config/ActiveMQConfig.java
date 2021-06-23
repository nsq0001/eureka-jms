package com.eureka.jms.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @author nsq
 * @Title:
 * @Package
 * @Description:
 * @date 2021-06-2215:57
 */
@Configuration
public class ActiveMQConfig {

	@Value("${spring.activemq.broker-url}")
	private String brokerUrl;


	@Bean
	public Queue queue() {
		return new ActiveMQQueue("ActiveMQQueue");
	}

	@Bean
	public Topic topic() {
		return new ActiveMQTopic("ActiveMQTopic");
	}


	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		return new ActiveMQConnectionFactory(brokerUrl);
	}

	/**
	 * Queue模式连接注入
	 */
	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ActiveMQConnectionFactory connectionFactory) {
		DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
		bean.setConnectionFactory(connectionFactory);

		bean.setConnectionFactory(connectionFactory);
		/**
		 * 客户端手动确认，这就意味着AcitveMQ将不会自动ACK任何消息。
		 * 如果一个conmuser在消费结束前没有调用message.acknowledge()确认一个消息，
		 * 之后调用其他conmuser时会再次消费它，因为对于broker而言，那些尚未真正ACK的消息被视为未消费，
		 * 直到它被确认。
		 */
		bean.setSessionAcknowledgeMode(2);
		return bean;
	}

	/**
	 * Topic模式连接注入
	 *
	 * @param connectionFactory
	 * @return
	 */
	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ActiveMQConnectionFactory connectionFactory) {
		DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
		//设置为发布订阅方式, 默认情况下使用的生产消费者方式
		bean.setPubSubDomain(true);
		bean.setConnectionFactory(connectionFactory);
		return bean;
	}
}
