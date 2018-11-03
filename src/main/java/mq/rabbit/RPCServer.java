package mq.rabbit;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class RPCServer {

    private static final String RPC_QUEUE_NAME = "rpc_queue";
	
	public static void main(String[] args) throws IOException {
		Connection connection = RabbitMqConnectionBuildFactory.getConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);
		
		channel.basicQos(1);
		System.out.println("waitting  RPC requests......");
		
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				BasicProperties replyProps = new AMQP.BasicProperties()
														.builder()
														.correlationId(properties.getCorrelationId())
														.build();
				String response = "";
				try {
					String message = new String(body, "UTF-8");
					System.out.println("server.....接收 :  " + message);
					response = message + "=" + fib(Integer.parseInt(message));
			 	}
				catch (RuntimeException e){
					 System.out.println(" [.] " + e.toString());
				}
				finally {
					System.out.println("server.....返回  :  " + response);
					channel.basicPublish("", properties.getReplyTo(), replyProps, response.getBytes("UTF-8"));
					channel.basicAck(envelope.getDeliveryTag(), false);
					// RabbitMq consumer worker thread notifies the RPC server owner thread
					synchronized (this) {
						this.notify();
					}
				}
			}
		};
		
//		开启ack
		channel.basicConsume(RPC_QUEUE_NAME, false, consumer);
		
		// Wait and be prepared to consume the message from RPC client.
        while (true) {
            synchronized(consumer) {
        try {
              consumer.wait();
            } catch (InterruptedException e) {
              e.printStackTrace();          
            }
            }
         }
	}
	/**
	 * 返回斐波纳挈数列第n个数值
	 * @param n
	 * @return
	 */
	public static int fib(int n) {
		if(n == 0) return 0;
		if(n == 1) return 1;
		return fib(n-1) + fib(n-2);
	}
	
}
