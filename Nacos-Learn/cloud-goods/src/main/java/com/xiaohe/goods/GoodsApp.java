package com.xiaohe.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author : 小何
 * @Description :
 * @date : 2023-10-27 14:32
 */
@SpringBootApplication
@EnableDiscoveryClient // 打开开关，开启服务的注册与发现功能。
@RibbonClient(name="cloud-goods", configuration = {RibbonClient.class})
public class GoodsApp {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApp.class, args);
    }
}
