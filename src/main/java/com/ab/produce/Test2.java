package com.ab.produce;

public class Test2 {

    public static void main(String[] args) {
        Bucket bucket = new Bucket(5);

        Consumer consumer = new Consumer(bucket);
        Producer producer = new Producer(bucket);
        consumer.start();
        producer.start();
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