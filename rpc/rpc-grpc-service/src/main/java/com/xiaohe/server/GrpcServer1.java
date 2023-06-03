package com.xiaohe.server;

import com.xiaohe.service.HelloServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * @ClassName GrpcServer1
 * @Description TODO
 * @Author 何
 * @Date 2023-06-03 18:54
 * @Version 1.0
 */
public class GrpcServer1 {
    public static void main(String[] args) throws InterruptedException, IOException {
        // 1. 绑定端口
        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(9000);
        // 2. 发布服务
        serverBuilder.addService(new HelloServiceImpl());
        // 3. 创建服务
        Server server = serverBuilder.build();
        server.start();
        server.awaitTermination();

    }
}
