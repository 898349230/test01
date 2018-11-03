package jvm.ch4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestJConsole2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        creatBusyThread();
        br.readLine();
        createSynThread(new Object());
    }

    /**
     * 死循环线程
     */
    public static void creatBusyThread(){

        Thread busyThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){

                }
            }
        }, "busyThread");
        busyThread.start();
    }

    /**
     * 
     * @param lock
     */
    public static void createSynThread(final Object lock){
        Thread synThread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {

                    }
                }
            }
        }, "synThread");

        synThread.start();
    }


}
