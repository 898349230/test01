package jvm.ch4;

import java.util.ArrayList;
import java.util.List;

public class TestJConsole {
    public static int i = 0;
    public static void main(String[] args){
        List<Integer[]> list = new ArrayList<>();
        Integer[] arr = new Integer[1024 * 64];
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(100);
                        list.add(arr);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i++);
                }
            }
        });

        thread.start();
    }
}
