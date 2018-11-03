package com.ab.mypatern.agent;

public class Agent implements RentHouse{

	RentHouse owner = new Owner();
	@Override
	public void showHouse() {
		System.out.println("代理看房子");
	}

	@Override
	public void getMomey() {
		owner.getMomey();
		//System.out.println("代理收钱");
	}

	@Override
	public void sign() {
		System.out.println("代理签字");
	}

}
