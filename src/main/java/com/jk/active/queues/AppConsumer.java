package com.jk.active.queues;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class AppConsumer {
    private static final String url = "tcp://10.15.208.165:61616";
    private static final String queueName = "queue-test";

    public static void main(String[] args) throws JMSException {

        //创建连接工厂connectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        //创建连接
        Connection connection = connectionFactory.createConnection();
        //启动连接
        connection.start();
        //创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //指定目标地址
        Destination destination = session.createQueue(queueName);
        //创建一个消费者
        MessageConsumer consumer = session.createConsumer(destination);
        //创建一个监听器
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("接收消息："+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //connection.close();
    }
}
