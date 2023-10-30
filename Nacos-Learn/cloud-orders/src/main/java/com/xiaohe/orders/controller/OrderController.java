package com.xiaohe.orders.controller;

import com.xiaohe.entity.Goods;
import com.xiaohe.orders.api.GoodsAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : 小何
 * @Description :
 * @date : 2023-10-27 14:44
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private GoodsAPI goodsAPI;


    @RequestMapping("/save")
    public Map save() {
        // 从 cloud-goods 服务中获取商品信息
        Goods goods = goodsAPI.findById("1");

        return new HashMap() {
            {
                put("code", 200);
                put("message", "success");
                put("goods", goods);
            }
        };
    }
}
