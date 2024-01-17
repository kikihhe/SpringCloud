package com.xiaohe.service;

import io.grpc.stub.StreamObserver;

/**
 * @ClassName HelloServiceImpl
 * @Description 实现HelloServiceImplBase
 * @Author 何
 * @Date 2023-06-03 18:45
 * @Version 1.0
 */
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void hello(HelloProto.HelloRequest request, StreamObserver<HelloProto.HelloResponse> responseObserver) {
        // 接收客户端提交的参数
        String name = request.getName();

        // 业务处理
        System.out.println(name);



        // 提供返回值
        // 1. 创建响应对象的构造者
        HelloProto.HelloResponse.Builder builder = HelloProto.HelloResponse.newBuilder();
        // 2. 给响应对象填充参数
        builder.setResult("调用成功, name: " + name);
        // 3. 将返回值返回
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}
