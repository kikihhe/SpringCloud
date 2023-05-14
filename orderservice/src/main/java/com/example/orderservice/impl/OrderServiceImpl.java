package com.example.orderservice.impl;


import com.example.feignclient.domain.Order;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : 小何
 * @Description :
 * @date : 2022-11-13 18:34
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public Order getOne(String id) {
        return orderMapper.getOne(id);
    }
}
