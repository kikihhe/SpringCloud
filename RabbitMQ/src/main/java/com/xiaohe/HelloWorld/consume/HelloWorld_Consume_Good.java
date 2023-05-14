package com.xiaohe.HelloWorld.consume;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author : 小何
 * @Description :
 * @date : 2022-11-20 18:52
 */
@Slf4j
public class HelloWorld_Consume_Good {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 1.建立连接
        Connection connection = ConnectionUtil.getConnection();
        // 2.创建通道Channel
        Channel channel = connection.createChannel();

        // 3.声明队列
        String queueName = "simple.queue";
        channel.queueDeclare(queueName, false, false, false, null);


        // 这里只是声明一个回调函数，并不是真正执行，等消息过来才会执行,而且是新的线程执行。
        // 其他线程执行回调函数。主线程不阻塞等待.
        DefaultConsumer consume = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                // 5.处理消息
                String message = new String(body);
                log.debug("接收到信息: {}", message);

            }
        };
        // 4.监听队列, 订阅消息
        // 自动ACK
        channel.basicConsume(queueName, true, consume);
    }
}
