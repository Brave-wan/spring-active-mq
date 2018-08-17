package com.jk.jms;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppProducer {

    public static void main(String args[]) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("producer.xml");
        ProducerService service = context.getBean(ProducerService.class);

        for (int i = 0; i < 1000; i++) {
            service.sendMessage("test" + i);
        }
        context.close();
    }

}
