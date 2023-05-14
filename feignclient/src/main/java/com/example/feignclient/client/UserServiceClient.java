package com.example.feignclient.client;


import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : 小何
 * @Description :
 * @date : 2022-11-14 12:56
 */
@FeignClient("userservice")
public interface UserServiceClient {
    @GetMapping("/person/getOne")
    public String getOne(@RequestParam("id") String id);
}
