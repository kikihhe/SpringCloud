import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @ClassName GrpcClient1
 * @Description TODO
 * @Author 何
 * @Date 2023-06-03 18:59
 * @Version 1.0
 */
public class GrpcClient1 {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9000).usePlaintext().build();
        HelloServiceGrpc.HelloServiceBlockingStub helloService = HelloServiceGrpc.newBlockingStub(channel);
        HelloProto.HelloRequest.Builder builder = HelloProto.HelloRequest.newBuilder();
        builder.setName("小明");
        HelloProto.HelloRequest helloRequest = builder.build();
        HelloProto.HelloResponse helloResponse = helloService.hello(helloRequest);
        System.out.println(helloResponse.getResult());

    }
}
