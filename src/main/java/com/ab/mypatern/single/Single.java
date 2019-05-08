package com.ab.mypatern.single;
/**
 * 静态内部类形式单例
 */
public class Single {

    private static class singleHolder{
        public static Single instance = new Single();
    }

    private Single(){}
    public static Single getInstance(){
        return singleHolder.instance;
    }

    public static void main(String[] args) {

    }

}
