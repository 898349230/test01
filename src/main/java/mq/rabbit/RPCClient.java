package mq.rabbit;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.rabbitmq.client.*;
import com.rabbitmq.client.AMQP.BasicProperties;import com.rabbitmq.client.AMQP.Channel.Close;

public class RPCClient {
	
	private String replayQueueName;
	private String requestQueueName = "rpc_queue"; 
	private Connection connection;
	private Channel channel;
	
	public RPCClient() throws IOException {
		connection = RabbitMqConnectionBuildFactory.getConnection();
		channel = connection.createChannel();
		replayQueueName = channel.queueDeclare().getQueue();
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException, IOException, InterruptedException {
		System.out.println("开始调用server。。。。。");
		RPCClient client = new RPCClient();
		String reply = client.call(""+9);
		System.out.println("reply --> " + reply);
		System.out.println("结束调用server。。。。。");
		client.close();
	}
	
	public String call(String message) throws UnsupportedEncodingException, IOException, InterruptedException {
//		指定一个关联id
		String correlationId = UUID.randomUUID().toString();
//		设置配置文件
		BasicProperties props = new AMQP.BasicProperties.Builder()
											.correlationId(correlationId)
											.replyTo(replayQueueName)
											.build();
//		向server端发送消息
		channel.basicPublish("", requestQueueName, props, message.getBytes("UTF-8"));
//		使用 容量为1的阻塞队列存放server的返回
		final BlockingQueue<String> response = new ArrayBlockingQueue<>(1);
//		接受server端返回的消息值, queue 为： replayQueueName  
		channel.basicConsume(replayQueueName, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
//				比对 关联id
				if(correlationId.equals(properties.getCorrelationId())) {
//					将server返回的消息放到阻塞队列中  
					response.offer(new String(body, "UTF-8"));
				}
			}
		});
//		返回阻塞队列中的信息
		return response.take();
	}
	
	private void close() {
		if(null != connection) {
			try {
				connection.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("close 关闭失败");
			}
		}
	}
}
