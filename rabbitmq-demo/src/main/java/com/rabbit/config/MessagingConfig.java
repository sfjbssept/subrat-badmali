package com.rabbit.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.ConnectionFactory;

@Configuration
public class MessagingConfig {
	
	public static final String QUEUENMAE = "Employee-queue";
	public static final String ROUTIG_KEY = "employee-routingKey";
	public static final String EXCHANGENAME = "Employee-exchange";
	
	@Bean
	public Queue queue() {
		return new Queue(QUEUENMAE);
	}
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(EXCHANGENAME);
	}
	
	@Bean
	public Binding binding(Queue queue,TopicExchange topicExchange) {
		return BindingBuilder.bind(queue).to(topicExchange).with(ROUTIG_KEY);
	}
	
	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate template(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate((org.springframework.amqp.rabbit.connection.ConnectionFactory) connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
		
	}
	

}
