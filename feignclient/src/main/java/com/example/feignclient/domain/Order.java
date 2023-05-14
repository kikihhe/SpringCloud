package com.example.feignclient.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author : 小何
 * @Description :
 * @date : 2022-11-13 18:31
 */
@Data
@TableName("sys_order")
public class Order {
    private String id;
    private String name;
    private String personname;
}
