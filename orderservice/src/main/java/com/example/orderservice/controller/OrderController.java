package com.example.orderservice.controller;


import com.alibaba.fastjson2.JSONObject;


import com.example.feignclient.client.UserServiceClient;
import com.example.feignclient.domain.Order;
import com.example.feignclient.domain.Person;
import com.example.orderservice.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 小何
 * @Description :
 * @date : 2022-11-13 18:35
 */
@RequestMapping("/order")
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserServiceClient userClient;

    @GetMapping("/getOne")
    public String getOne(String id) throws JsonProcessingException {
        Order one = orderService.getOne(id);
        // 在这里远程调用userservice的方法，获得对应订单是谁下的单
        String person = userClient.getOne(id);
        Person person1 = JSONObject.parseObject(person, Person.class);
        one.setPersonname(person1.getName());

        return JSONObject.toJSONString(one);

    }
}
