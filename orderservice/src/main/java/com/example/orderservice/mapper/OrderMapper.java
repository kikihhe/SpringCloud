package com.example.orderservice.mapper;



import com.example.feignclient.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author : 小何
 * @Description :
 * @date : 2022-11-13 18:29
 */
@Mapper
public interface OrderMapper {
    public Order getOne(@Param("id") String id);
}
