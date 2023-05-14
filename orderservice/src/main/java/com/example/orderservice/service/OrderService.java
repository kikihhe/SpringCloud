package com.example.orderservice.service;


import com.example.feignclient.domain.Order;

/**
 * @author : 小何
 * @Description :
 * @date : 2022-11-13 18:33
 */
public interface OrderService {
    public Order getOne(String id);
}
