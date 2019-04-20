package dubbo;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;

public class DubboTestProvider {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring/dubbo-provider.xml");
//        启动spring上下文
        context.start();
        try {

            System.in.read();
        }catch (Exception e){
            System.out.println("error....");
        }
    }
}
