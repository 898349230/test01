package com.ab.thread;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintABC2{

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();

        new Thread(new Printer(lock, conditionA, conditionB, 'A')).start();
        new Thread(new Printer(lock, conditionB, conditionC, 'B')).start();
        new Thread(new Printer(lock, conditionC, conditionA, 'C')).start();

    }

}

class Printer implements Runnable{
    private Lock lock;
    private Condition thisCondition ;
    private Condition next ;
    private char c;

    public Printer(Lock lock, Condition thisCondition, Condition next, char c) {
        this.lock = lock;
        this.thisCondition = thisCondition;
        this.next = next;
        this.c = c;
    }

    @Override
    public void run() {
        while(true){
            lock.lock();
            System.out.println(c);
            try {
                Thread.sleep(500);
                next.signal();
                thisCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

    }
}