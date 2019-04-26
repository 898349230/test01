package com.ab.produce;

public class Bucket {
    private int i;
    private int num;

    public Bucket(int num) {
        this.num = num;
    }

    public synchronized void put(){
        while(i < num){
            i++;
            System.out.println("生产者 i = " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
    }
    public synchronized void get(){
        while(i>0){
            i--;
            System.out.println("消费者 i = " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
    }
}
