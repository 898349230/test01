package dubbo.impl;

import dubbo.api.IHelloService;

public class HelloServiceImpl01 implements IHelloService {
    @Override
    public String sayHello(String str) {
        System.out.println("service 被调用。。。");
        return "return is " + str;
    }
}
