package com.zs.jms.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * User: chanson-pro
 * Date-Time: 2017-10-30 10:34
 * Description:消息中间件
 */
public class AppProducer {
    private static final String url = "tcp://localhost:61616";
    private static final String queueName = "queue-test";
    public static void main(String[] args) throws JMSException {
        // 1.创建工厂，根据服务商创建
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        // 2.创建连接
        Connection connection = connectionFactory.createConnection();
        // 3.启动链接
        connection.start();
        // 4.创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 5.创建一个目标
        Destination destination = session.createQueue(queueName);
        // 6.创建一个生产者，向目标发送消息
        MessageProducer producer = session.createProducer(destination);

        for (int i = 0; i <100 ; i++) {
            // 7.创建一个消息
            TextMessage textMessage = session.createTextMessage("test" + i);
            // 8.创建者向目的地发送消息
            producer.send(textMessage);
            System.out.println("发送消息" + textMessage.getText());
        }
        // 9.关闭连接
        connection.close();
    }

}
