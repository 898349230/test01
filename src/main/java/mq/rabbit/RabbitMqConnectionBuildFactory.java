package mq.rabbit;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMqConnectionBuildFactory {

	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private static final String HOST = "localhost";
	private static final Integer PORT = 5672 ;
	private static final String VIR_HOST = "/";
	private static ConnectionFactory factory = null;
	private static Connection conn = null;
	
	public static Connection getConnection() {
		if(null == conn) {
			factory = new ConnectionFactory();
			factory.setUsername(USERNAME);
			factory.setPassword(PASSWORD);
			factory.setHost(HOST);
			factory.setVirtualHost(VIR_HOST);
			factory.setPort(PORT);
			try {
				 conn = factory.newConnection();
			} catch (IOException | TimeoutException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
}
