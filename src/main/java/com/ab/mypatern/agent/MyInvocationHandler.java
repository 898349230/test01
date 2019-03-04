package com.ab.mypatern.agent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler{

    /**
     * 被代理的对象
     */
    private RentHouse rentHouse ;

    public MyInvocationHandler(RentHouse rentHouse) {
        this.rentHouse = rentHouse;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj = null;
        if(method.getName().equals("getMomey")){
            System.out.println(" 开始收钱。。。。 start ");
//            执行被代理对象的对应方法
            obj =  method.invoke(rentHouse, args);
            System.out.println(" 结束收钱。。。。 end ");
        }

        return obj;
    }
}
