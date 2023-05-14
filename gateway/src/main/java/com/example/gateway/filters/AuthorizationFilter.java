package com.example.gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * @author : 小何
 * @Description :
 * @date : 2022-11-14 19:08
 */
@Component
@Order(1)
public class AuthorizationFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求对象
        ServerHttpRequest request = exchange.getRequest();
        // 获取请求参数
        MultiValueMap<String, String> params = request.getQueryParams();
        // 查看请求参数中是否有authorization
        String authorization = params.getFirst("authorization");

        // 如果没有，或者不是"admin",就返回
        ServerHttpResponse response = exchange.getResponse();
        if(authorization == null || "".equals(authorization) || !"admin".equals(authorization)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        // 到这里就代表认证成功
        return chain.filter(exchange);

    }
}
