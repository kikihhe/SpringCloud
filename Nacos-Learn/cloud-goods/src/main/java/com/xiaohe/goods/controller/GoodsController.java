package com.xiaohe.goods.controller;

import com.xiaohe.entity.Goods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 小何
 * @Description :
 * @date : 2023-10-27 14:34
 */
@Slf4j
@RestController
@RequestMapping("/goods")
public class GoodsController {


    @RequestMapping("/findById/{id}")
    public Goods findById(@PathVariable("id") String id) {
        log.info("id: {}", id);
        return new Goods("小米", 90);
    }



}
