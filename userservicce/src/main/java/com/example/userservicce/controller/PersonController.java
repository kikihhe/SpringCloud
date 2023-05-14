package com.example.userservicce.controller;

import com.alibaba.fastjson2.JSON;

import com.example.feignclient.domain.Person;
import com.example.userservicce.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 小何
 * @Description :
 * @date : 2022-11-13 16:26
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/getOne")
    public String getOne(String id) {
        Person person = personService.getOneById(id);
        return JSON.toJSONString(person);
    }

    @GetMapping("/getList")
    public String getList() {
        return JSON.toJSONString(personService.getList());
    }
}
