package com.xiaohe.Work.publish;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 */
public class Work_Publish {
    public static void main(String[] args) throws IOException, TimeoutException {

            // 1.建立连接
            Connection connection = ConnectionUtil.getConnection();
            // 2.创建通道Channel
            Channel channel = connection.createChannel();

            // 3.声明队列
            String queueName = "simple.queue";
            channel.queueDeclare(queueName, false, false, false, null);

            // 4.发送消息
            for (int i = 0; i < 100; i++) {
                String message = i + " hello, rabbitmq!";
                channel.basicPublish("", queueName, null, message.getBytes());
            }
            System.out.println("发送消息成功");

            // 5.关闭通道和连接
            channel.close();
            connection.close();

    }
}
