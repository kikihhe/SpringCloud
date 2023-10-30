package com.xiaohe.goods.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : 小何
 * @Description :
 * @date : 2023-10-28 13:13
 */
@Configuration
public class RibbonRule {
    @Bean
    public IRule getRule() {
        return new RandomRule();
    }
}
