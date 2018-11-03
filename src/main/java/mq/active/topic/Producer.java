package mq.active.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {
    private static String BROKER_URL = "tcp://node1:61616";
    private static String TOPIC_NAME = "test-topic";
    public static void main(String[] args) throws InterruptedException {
        for(int i = 1; i< 100 ; i++){
            Thread.sleep(500);
            producer(i);
        }
    }

    public static void producer(int i){
        ConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);
        try {
            Connection connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
//            topic  每个消息都会被每个消费者消费
            Topic topic = session.createTopic(TOPIC_NAME);
            MessageProducer producer = session.createProducer(topic);
            TextMessage message = session.createTextMessage("hello" + "\t" + i);
            producer.send(message);
            System.out.println("发送  " + message.getText());
            producer.close();
            producer.close();
            session.commit();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }finally{

        }
    }

}
