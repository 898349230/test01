package mq.rabbit;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class RMQSender {

	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private static final String HOST = "localhost";
	private static final Integer PORT = 5672 ;
	private static final String VIR_HOST = "/";
	private static final String QUEUE_NAME = "task_queue_exclusion";
	private static String QUEUE_NAME_ONE = "task_queue_one";
	
	private static ConnectionFactory factory = null;
	private static Connection conn = null;
	
	private static String EXCHANGE_NAME = "logs";
	private static String EXCHANGE_NAME_ROUTING = "test_routing_key";
	private static String EXCHANGE_NAME_ROUTING_TOPIC = "test_routing_key_topic";
	
//	static {
//		factory = new ConnectionFactory();
//		factory.setUsername(USERNAME);
//		factory.setPassword(PASSWORD);
//		factory.setHost(HOST);
//		factory.setVirtualHost(VIR_HOST);
//		factory.setPort(PORT);
//		try {
//			 conn = factory.newConnection();
//		} catch (IOException | TimeoutException e) {
//			e.printStackTrace();
//		}
////		factory.setUri("amqp://userName:password@hostName:portNumber/virtualHost");
//	}
	
	public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {
//		发送一条消息
		one2OneSender(" 1");
		
//		测试两个 consumer同时接收一个producer生产的数据
//		先启动两个 consumer，再启动producer，会发现无论几个consumer，queue中的每一条消息都会按顺序轮询发送给consumer
//		for(int i = 1; i<= 10; i++) {
//			String num = ""; 
//			for(int j = 1 ; j <= i; j++) {
//				num += "."; 
//			}
//			testWorkQueue(new String[] {"第 " + i + " 条"," message" + num});
//		}
		
//		测试 exchange， 如果启动两个consumer，一个producer，每个consumer都会收到producer生产的每条消息
//		publish/subscribe模式
//		for(int i = 1; i<= 100; i++) {
//			String num = ""; 
//			for(int j = 1 ; j <= i; j++) {
//				num += "."; 
//			}
//			Thread.sleep(500);
//			testWorkQueueExchange(new String[] {"第 " + i + " 条"," message" + num});
//		}
		
//		测试routingKey
//		String[] arr = new String[] {"fatal", "error", "info", "warnning", "debug", "all"};
//		for(int i = 0 ; i < 10 ; i++) {
////			发送10条随机 routingKey的消息
//			testWorkQueueRoutingKey(arr);
//		}
		
//		测试routingKey为topic
//		for(int i = 0; i < 10; i++) {
//			testWorkQueueTopic();
//		}
	}
	
	/**
	 * 测试一个消费者一个生产者
	 * @param str
	 * @throws IOException
	 */
	private static void one2OneSender(String str) throws IOException {
		try {
			conn = RabbitMqConnectionBuildFactory.getConnection();
			Channel channel = conn.createChannel();
			String message = "hello_queue " + str;
			channel.basicPublish("", QUEUE_NAME_ONE, null, message.getBytes());
			System.out.println("send message : " + message);
//			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 测试一个生产者 多个消费者
	 * ack，持久化，
	 * exclusive : channel 只能被一个connection使用， (used by only one connection and the queue will be deleted when that connection closes)
	 * autoDelete : 没有订阅者时该队列删除，queue that has had at least one consumer is deleted when last consumer unsubscribes
	 * @param str
	 * @throws IOException
	 */
	private static void testWorkQueue(String[] str) throws IOException {
		Connection connection = RabbitMqConnectionBuildFactory.getConnection();
		Channel channel = connection.createChannel();
//		是否持久化， 持久化后server 重启后queue 依旧存在
		boolean durable = true;
//		channel 一旦建立，属性不可以更改，比如 durable再设为true
		channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
		String message = getMessage(str);
		System.out.println("send " + message);
//												持久化策略
		channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
		try {
			channel.close();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
	
	private static String getMessage(String[] strings){
	    if (strings.length < 1)
	        return "Hello World!";
	    return joinStrings(strings, " ");
	}
	
	/**
	 * 测试 exchange
	 * 
	 * @param str
	 * @throws IOException
	 */
	private static void testWorkQueueExchange(String[] str) throws IOException {
		Connection connection = RabbitMqConnectionBuildFactory.getConnection();
		Channel channel = connection.createChannel();
//		producer将消息给exchange，exchange将消息给consumer，  有四种类型direct, topic, headers ,fanout.
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

		String message = getMessage(str);
		System.out.println("send " + message);
		channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
		try {
			channel.close();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}

	private static String joinStrings(String[] strings, String delimiter) {
	    int length = strings.length;
	    if (length == 0) return "";
	    StringBuilder words = new StringBuilder(strings[0]);
	    for (int i = 1; i < length; i++) {
	        words.append(delimiter).append(strings[i]);
	    }
	    return words.toString();
	}

	/**
	 * RoutingKey ：用于过滤消息
	 * 将生成的消息按照routingKey绑定到exchange上，接收端则需要对应的exchange和routingKey才能接受到消息
	 * @throws IOException 
	 * 
	 */
	private static void testWorkQueueRoutingKey(String[] args) throws IOException {
		Connection connection = RabbitMqConnectionBuildFactory.getConnection();
		Channel channel = connection.createChannel();
//		type 为 direct
		channel.exchangeDeclare(EXCHANGE_NAME_ROUTING, BuiltinExchangeType.DIRECT);
//		随机生成日志级别作为routingKey
		String serverity = getSeverity(args);
		String message = getMessage(args);
//		发布消息 routingKey为serverity
		channel.basicPublish(EXCHANGE_NAME_ROUTING, serverity, null, message.getBytes("utf-8"));
		System.out.println("send  routingKey:" + serverity + "  message:" + message);
//		try {
//			channel.close();
//		} catch (TimeoutException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		connection.close();
	}

	private static String getSeverity(String[] strings) {
//		if (strings.length < 1)
//			return "info";
		int i = new Random().nextInt(strings.length);
		return strings[i];
	}

	/**
	 * 测试routingKey为topic
	 * topic模式支持匹配
	 *   "*.*.rabbit", "#.rabbit", "*.orange.*", "lazy.#"
	 *   各个关键字之间用 “.”隔开
	 *   * 表示匹配一个    * (star) can substitute for exactly one word.
	 *   # 表示匹配所有    # (hash) can substitute for zero or more words.
	 *   如果一个producer产生的消息同时匹配到多个routingKey，该消息也只能被分发到一个queue中
	 * @param args
	 * @throws IOException
	 */
	private static void testWorkQueueTopic() throws IOException, TimeoutException {
		Connection connection = RabbitMqConnectionBuildFactory.getConnection();
		Channel channel = connection.createChannel();
//		生命exchange类型为topic
		channel.exchangeDeclare(EXCHANGE_NAME_ROUTING_TOPIC, BuiltinExchangeType.TOPIC);
//		随机生成 一定规则的routingKey作为测试
		String routingKey = getRoutingKey4Topic();
		System.out.println("send " + " routingKey:" + routingKey + "  message: " + routingKey);
		channel.basicPublish(EXCHANGE_NAME_ROUTING_TOPIC, routingKey, null, routingKey.getBytes());
		channel.close();
	}

	/**
	 * 模拟生成message
	 * 格式： lazy.black.rabbit , 符合routingKey匹配规则
	 * @return
	 */
	private static String getRoutingKey4Topic() {
		String[] adj = new String[]{"lazy", "not lazy"};
		String[] color = new String[]{"yellow", "orange", "black"};
		String[] animal = new String[]{"cat", "rabbit", "cow", "dog"};
		Random r = new Random();
		String message = adj[r.nextInt(adj.length)] + "." + color[r.nextInt(color.length)] + "." + animal[r.nextInt(animal.length)];
		if(r.nextInt(11) > 7) {
//			30%的概率生成的routingKey不符合规则
			message += ".male";
		}
		return message;
	}

	private static String getRoutingKey(String[] args) {
		return args[new Random().nextInt(args.length)];
	}
	
}
