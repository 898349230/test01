package mq.active.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class Consumer {
    private static String BROKER_URL = "tcp://node1:61616";
    private static String QUEUE_NAME = "test-queue";
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
            Queue queue = session.createQueue(QUEUE_NAME);
            MessageConsumer consumer = session.createConsumer(queue);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if(message instanceof TextMessage){
                        TextMessage message1 = (TextMessage) message;
                        try {
                            System.out.println("接收  " + message1.getText());
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
