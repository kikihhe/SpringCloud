package com.example.userservicce.service;


import com.example.feignclient.domain.Person;

import java.util.List;

/**
 * @author : 小何
 * @Description :
 * @date : 2022-11-13 16:24
 */
public interface PersonService {
    public Person getOneById(String id);
    public List<Person> getList();
}
