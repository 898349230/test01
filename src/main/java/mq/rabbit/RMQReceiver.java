package mq.rabbit;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.*;
import org.apache.activemq.broker.ConsumerBrokerExchange;
import org.springframework.format.support.FormatterPropertyEditorAdapter;

import com.rabbitmq.client.AMQP.BasicProperties;

public class RMQReceiver {

    private static final String QUEUE_NAME = "task_queue_exclusion";
    private static String EXCHANGE_NAME = "logs";
    private static String EXCHANGE_NAME_ROUTING = "test_routing_key";
    private static String EXCHANGE_NAME_ROUTING_TOPIC = "test_routing_key_topic";
    private static String QUEUE_NAME_ONE = "task_queue_one";
    private static Connection connection;

    public static void main(String[] args) throws IOException {
//		接受一条消息
//        one2OneRecever();

//		测试两个 consumer同时接收一个producer生产的数据
//		先启动两个 consumer，再启动producer，会发现无论几个consumer，queue中的每一条消息都会按顺序轮询发送给consumer
		testWorkQueue();

//		测试 exchange
//		testWorkQueueExchange();

//		测试routingKey
//		String[] arr = new String[] {"fatal", "error", "info", "warnning", "debug"};
//		testWorkQueueRoutingKey(arr);

//		测试routingKey为topic
//		String[] arr = new String[] {"*.*.rabbit", "#.rabbit", "*.orange.*", "lazy.#"};
//		testWorkQueueTopic(arr);
    }

    /**
     * 测试一个消费者一个生产者
     *
     * @throws IOException
     */
    private static void one2OneRecever() {
        Connection connection = RabbitMqConnectionBuildFactory.getConnection();
        try {
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME_ONE, false, false, false, null);
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
                                           byte[] body) throws IOException {
                    String message = new String(body, "utf-8");
                    System.out.println("reveive : " + message);
                }
            };
            String consumerTag = channel.basicConsume(QUEUE_NAME_ONE, true, consumer);
            System.out.println("consumerTag " + consumerTag);
            System.in.read();
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试一个生产者 多个消费者
     * ack，
     * 持久化，
     *
     * @throws IOException
     */
    private static void testWorkQueue() throws IOException {
        Connection connection = RabbitMqConnectionBuildFactory.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
//		每次ack之前都从queue中接受一条消息，可能会导致queue中消息过多
        channel.basicQos(1);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
                    throws IOException {
                System.out.println("start ");
                String message = new String(body, "utf-8");
                System.out.println("reveive : " + message + " consumerTag : " + consumerTag + " isRedeliver() : " + envelope.isRedeliver());
                try {
                    doWork(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
//                    确认消息， 如果autoAck = false 必须代码确认消息，否则该条消息一直处于 Unacked 状态，没有被消费掉
//                    channel.basicAck(envelope.getDeliveryTag(), false);
                    System.out.println("envelope.getDeliveryTag() : " + envelope.getDeliveryTag());
                    System.out.println("Done ");
                }
            }
//            隐示取消
            @Override
            public void handleCancel(String consumerTag) {
                System.out.println("handleCancel...  consumerTag = " + consumerTag);
            }
//            显示取消
            @Override
            public void handleCancelOk(String consumerTag) {
                System.out.println("handleCancelOk...  consumerTag = " + consumerTag);
            }
//          会在任何其他回调之前传递消费者标签给Consumer
            @Override
            public void handleConsumeOk(String consumerTag) {
                System.out.println("handleConsumeOk...  consumerTag = " + consumerTag);
            }
//            当通道和连接关闭时
            @Override
            public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
                System.out.println("handleShutdownSignal...  consumerTag = " + consumerTag);
            }
        };
//		true 时 ack 关闭  , 如果一个worker意外挂了，已经分发给这个worker的消息都不会被执行了
//		boolean autoAck = true;

//		set this flag to false and send a proper acknowledgment from the worker, once we're done with a task.
//		ack 开启 , 如果一个worker意外挂了，已经分发给这个worker的未执行的，未成功执行的消息都会被重新发送给其他健康的worker，保证queue中的每条消息都被执行
//				 如果server stop了，消息还是会丢
        boolean autoAck = false;
        String consumerTag = "myConsumerTag";
        channel.basicConsume(QUEUE_NAME, autoAck, consumerTag, consumer);
//        显式地检索消息, 随机从queue中获取一条消息
//        GetResponse response = channel.basicGet(QUEUE_NAME, autoAck);
//        if(null == response){
//
//        }else{
//            BasicProperties props = response.getProps();
//            System.out.println(" ContentType : " + props.getContentType());
//            byte[] body = response.getBody();
//            String message = new String(body, "utf-8");
//            System.out.println("response message " + message + " DeliveryTag = " + response.getEnvelope().getDeliveryTag());
//        }

//        明确地取消一个特定的Consumer
//        channel.basicCancel(consumerTag);

//        try {
//        关闭通道
//            channel.close();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//        }
    }

    private static void doWork(String message) throws InterruptedException {
        for (char ch : message.toCharArray()) {
            if (ch == '.'){
//		        模拟忙碌状态，线程sleep
//                Thread.sleep(1000);
            }
        }
    }

    /**
     * 测试 exchange
     *
     * @throws IOException
     */
    private static void testWorkQueueExchange() throws IOException {
        Connection connection = RabbitMqConnectionBuildFactory.getConnection();
        Channel channel = connection.createChannel();
//		随机生成一个 non-durable, exclusive, autodelete 的queue
        String queueName = channel.queueDeclare().getQueue();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
//		queue与 exchange绑定， fanout 类型不需要 routingKey，忽略为"" 
//		只有当这个channel启动时consumer才会从exchange中拉取即时消息，之前的消息不会再次拉取
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println(queueName + " start...");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "utf-8");
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }

    /**
     * RoutingKey ：用于过滤消息
     * 将生成的消息按照routingKey绑定到exchange上，接收端则需要对应的exchange和routingKey才能接受到消息
     *
     * @throws IOException
     */
    private static void testWorkQueueRoutingKey(String[] args) throws IOException {
        Connection connection = RabbitMqConnectionBuildFactory.getConnection();
        Channel channel = connection.createChannel();
        String queueName = channel.queueDeclare().getQueue();
        for (String serverity : args) {
//			将不同级别的routingKey和exchange，queue绑定
            channel.queueBind(queueName, EXCHANGE_NAME_ROUTING, serverity);
        }
        System.out.println("receive start...");
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "utf-8");
                System.out.println(envelope.getDeliveryTag() + " routingKey:" + envelope.getRoutingKey() + "  " + "reveive :" + message);
            }
        };
        channel.basicConsume(queueName, false, consumer);
    }

    /**
     * 测试routingKey为topic
     * topic模式支持匹配
     * "*.*.rabbit", "#.rabbit", "*.orange.*", "lazy.#"
     * 各个关键字之间用 “.”隔开
     * * 表示匹配一个    * (star) can substitute for exactly one word.
     * # 表示匹配所有    # (hash) can substitute for zero or more words.
     * 如果一个producer产生的消息同时匹配到多个routingKey，该消息也只能被分发到一个queue中
     *
     * @param args
     * @throws IOException
     */
    private static void testWorkQueueTopic(String[] args) throws IOException {
        Connection connection = RabbitMqConnectionBuildFactory.getConnection();
        Channel channel = connection.createChannel();
        String queueName = channel.queueDeclare().getQueue();
        channel.exchangeDeclare(EXCHANGE_NAME_ROUTING_TOPIC, BuiltinExchangeType.TOPIC);
        for (String routingKey : args) {
//			绑定
            channel.queueBind(queueName, EXCHANGE_NAME_ROUTING_TOPIC, routingKey);
        }
        System.out.println("receive start...");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "utf-8");
                System.out.println(envelope.getDeliveryTag() + "  routingKey:" + envelope.getRoutingKey() + "  message:" + message);
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
}
