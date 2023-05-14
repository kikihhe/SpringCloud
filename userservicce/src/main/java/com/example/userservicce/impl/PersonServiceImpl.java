package com.example.userservicce.impl;


import com.example.feignclient.domain.Person;
import com.example.userservicce.mapper.PersonMapper;
import com.example.userservicce.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : 小何
 * @Description :
 * @date : 2022-11-13 16:25
 */
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonMapper personMapper;
    @Override
    public Person getOneById(String id) {
        return personMapper.getOneById(id);
    }

    @Override
    public List<Person> getList() {
        return personMapper.getList();
    }
}
