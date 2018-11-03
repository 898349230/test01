package com.ab.mypatern.agent;

public class Owner implements RentHouse{

	@Override
	public void showHouse() {
		System.out.println("房主看房");
	}

	@Override
	public void getMomey() {
		System.out.println("房主收钱");
	}

	@Override
	public void sign() {
		System.out.println("房主签字");
	}

}
