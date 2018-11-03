package mq.active.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class Consumer {
    private static String BROKER_URL = "tcp://node1:61616";
    private static String TOPIC_NAME = "test-topic";
    public static void main(String[] args){
         consumer();
    }

    public static void consumer(){
        ConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = null;
        try {
            connection = factory.createConnection();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        Session session = null;
        try {
            connection.start();
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic(TOPIC_NAME);
            MessageConsumer consumer = session.createConsumer(topic);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if(message instanceof TextMessage){
                        TextMessage textMessage = (TextMessage) message;
                        try {
                            System.out.println("接收  " + textMessage.getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            System.in.read();
            session.commit();
            consumer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
