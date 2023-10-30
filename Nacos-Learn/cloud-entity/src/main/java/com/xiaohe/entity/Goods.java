package com.xiaohe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zed
 * @date 2022/6/9 0:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    private String name;
    private Integer price;
}
