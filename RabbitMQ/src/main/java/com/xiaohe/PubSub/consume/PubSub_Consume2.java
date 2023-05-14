package com.xiaohe.PubSub.consume;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 发布订阅模式的消费者
 */
@Slf4j
public class PubSub_Consume2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 1. 获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 2. 创建通道
        Channel channel = connection.createChannel();
        String queueName = "my_fanout_queue1";
        String exchangeName = "my_fanout_exchange";
        // 3. 声明交换机
        channel.exchangeDeclare(exchangeName, "fanout");
        // 4. 声明队列
        channel.queueDeclare(queueName,false, false, false, null);
        channel.basicQos(1);

        // 5. 将队列绑定在交换机上,不指定路由键
        channel.queueBind(queueName, exchangeName, "");

        // 6. 监听队列
        DefaultConsumer consume = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {


                String message = new String(body);
                log.debug("接收到信息: {}", message);

            }
        };
        channel.basicConsume(queueName,true, consume);

    }
}
