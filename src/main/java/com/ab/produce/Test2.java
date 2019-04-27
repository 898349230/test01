package com.ab.produce;

public class Test2 {

    public static void main(String[] args) {
        Bucket bucket = new Bucket(5);

//        Consumer consumer = new Consumer(bucket);
//        Producer producer = new Producer(bucket);
//        consumer.start();
//        producer.start();

        new Thread(new Consumer2(bucket)).start();
        new Thread(new Producer2(bucket)).start();

    }

}
class Bucket {
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

class Consumer extends Thread{
    private Bucket bucket;

    public Consumer(Bucket bucket) {
        this.bucket = bucket;
    }

    @Override
    public void run() {
        while(true){
            bucket.get();
        }
    }
}
class Producer extends Thread{
    private Bucket bucket;

    public Producer(Bucket bucket) {
        this.bucket = bucket;
    }

    @Override
    public void run() {
        while(true){
            bucket.put();
        }
    }
}

class Consumer2 implements Runnable{
    private Bucket bucket;

    public Consumer2(Bucket bucket) {
        this.bucket = bucket;
    }

    @Override
    public void run() {
        while(true){
            bucket.get();
        }
    }
}
class Producer2 implements Runnable{
    private Bucket bucket;

    public Producer2(Bucket bucket) {
        this.bucket = bucket;
    }

    @Override
    public void run() {
        while(true){
            bucket.put();
        }
    }
}