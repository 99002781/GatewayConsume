package com.rabbitmq.consumer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.Arrays;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

//import com.ram.model.Product;

@Component
public class Consumer implements ChannelAwareMessageListener
{

	@Override
	public void onMessage(Message message, com.rabbitmq.client.Channel channel) throws Exception
	{
		System.out.println("Received <" + message + ">");

		 byte[] body = message.getBody();
		 String receive=new String(body);
			/* String receive=message.toString(); */
		
		 System.out.println(receive);
			/*
			 * String[] words = receive.split(",");
			 * System.out.println(Arrays.toString(words)); System.out.println(words[0]);
			 */
		MessageProperties propss=message.getMessageProperties();
		 String qname=propss.getConsumerQueue();
		 
		 System.out.println(qname);
		//Product product = (Product) getObject(byteArray);
		//System.out.println("product = " + product);
		
		/**
		 * Suppose say here we are storing product details in the table
		 * and some exception occured then what we can do is do not acknowledge
		 * to Queue, so that it will be in queue and later we can pick up
		 * the same product from the queue and process it
		 */
		/*
		 * Positively acknowledge a single delivery, the message will be discarded
		 */
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}

	
}

