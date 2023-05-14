package com.example.userservicce.mapper;


import com.example.feignclient.domain.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : 小何
 * @Description :
 * @date : 2022-11-13 16:20
 */
@Mapper
public interface PersonMapper {

    public Person getOneById(@Param("id") String id);
    public List<Person> getList();

}
