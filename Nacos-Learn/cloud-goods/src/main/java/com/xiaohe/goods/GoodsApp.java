package com.xiaohe.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author : 小何
 * @Description :
 * @date : 2023-10-27 14:32
 */
@SpringBootApplication
@EnableDiscoveryClient // 打开开关，开启服务的注册与发现功能。
@RibbonClient(name="cloud-goods", configuration = {RibbonClient.class})
public class GoodsApp {
    public static void main(String[] args) throws IOException {
        FileChannel fileChannel = FileChannel.open(Paths.get("your_file_path"), StandardOpenOption.READ, StandardOpenOption.WRITE);
        MappedByteBuffer mappedBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileChannel.size());

        SpringApplication.run(GoodsApp.class, args);
    }
}
