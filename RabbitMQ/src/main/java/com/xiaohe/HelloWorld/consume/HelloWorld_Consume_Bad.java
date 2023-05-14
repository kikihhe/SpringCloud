package com.xiaohe.HelloWorld.consume;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author : 小何
 * @Description :
 * @date : 2022-11-20 18:53
 */
@Slf4j
public class HelloWorld_Consume_Bad {
    public static void main(String[] args) throws IOException, TimeoutException {
            // 1.建立连接
            Connection connection = ConnectionUtil.getConnection();
            // 2.创建通道Channel
            Channel channel = connection.createChannel();

            // 3.声明队列
            String queueName = "simple.queue";
            channel.queueDeclare(queueName, false, false, false, null);

            DefaultConsumer consume = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag,
                                           Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body) throws IOException {
                    // 性能极其差劲:
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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
