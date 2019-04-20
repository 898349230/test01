package dubbo;


import dubbo.api.IHelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboTestConsumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/dubbo-consumer.xml");
        IHelloService service = (IHelloService)context.getBean("service");
        String str = service.sayHello("获取服务成功");
        System.out.println(str);
    }

}
