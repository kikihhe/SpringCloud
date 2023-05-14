package com.xiaohe.PubSub.publish;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.ConnectionUtil;

import java.io.IOException;

import java.util.concurrent.TimeoutException;

/**
 * 发布订阅模式的生产者
 */
public class PubSub_Publish {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 1. 获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 2. 创建通道
        Channel channel = connection.createChannel();

        String exchangeName = "my_fanout_exchange";
        // 3. 声明交换机
        //    exchangeDeclare(String exchange, String type)
        //    exchange: 交换机名称
        //    type: 交换机类型
        channel.exchangeDeclare(exchangeName, "fanout");


        // 4. 发送消息
        for (int i = 0; i < 10; i++) {
            String message = i + " hello rabbitMQ!";
            channel.basicPublish(exchangeName, "", null, message.getBytes());
        }
        // 5. 关闭资源
        channel.close();
        connection.close();
    }
}
