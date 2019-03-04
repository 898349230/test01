package com.ab.mypatern.agent;

import java.lang.reflect.Proxy;

public class Test {
	public static void main(String[] args) {

//		普通代理
//		Agent agent = new Agent();
//		agent.showHouse();
//		agent.sign();
//		agent.getMomey();

//		动态代理
		RentHouse rentHouse = new Owner();
		RentHouse rentHouse2 = new Agent();
		MyInvocationHandler invocationHandler = new MyInvocationHandler(rentHouse);
		RentHouse proxcy = (RentHouse)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{RentHouse.class}, invocationHandler);

		proxcy.getMomey();

	}
}
