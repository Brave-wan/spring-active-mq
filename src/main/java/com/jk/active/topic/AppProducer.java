package com.jk.active.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class AppProducer {
    private static final String url = "tcp://10.15.208.165:61616";
    private static final String topicName = "topic-test";

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
        Destination destination = session.createTopic(topicName);
        //创建生产者
        MessageProducer producer = session.createProducer(destination);
        for (int i = 0; i < 100; i++) {
            //创建消息
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText("test" + i);
            //发布消息
            producer.send(textMessage);
            System.out.println("发送消息" + textMessage.getText());

        }
        //关闭连接
        connection.close();
    }
}
