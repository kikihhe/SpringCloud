package com.xiaohe.orders.fallback;

import com.xiaohe.entity.Goods;
import com.xiaohe.orders.api.GoodsAPI;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author : 小何
 * @Description :
 * @date : 2023-10-29 21:18
 */
@Slf4j
@Component
public class GoodsAPIClientFallbackFactory implements FallbackFactory<GoodsAPI> {
    @Override
    public GoodsAPI create(Throwable throwable) {
        // 当熔断降级被触发时执行这个方法，我们在此处返回给前端一个默认值或者友好的报错
        return new GoodsAPI() {
            @Override
            public Goods findById(String id) {
                log.error("远程调用 findById 错误，message : {}, id: {}", throwable.getMessage(), id);
                return new Goods();
            }
        };
    }
}
