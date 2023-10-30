package com.xiaohe.orders.api;

import com.xiaohe.entity.Goods;
import com.xiaohe.orders.fallback.GoodsAPIClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : 小何
 * @Description :
 * @date : 2023-10-28 14:54
 */
@FeignClient(name = "cloud-goods", fallbackFactory = GoodsAPIClientFallbackFactory.class) // 指定服务名
@RequestMapping("/goods") // 路径
public interface GoodsAPI {
    @RequestMapping("/findById/{id}")
    public Goods findById(@PathVariable("id") String id);
}
