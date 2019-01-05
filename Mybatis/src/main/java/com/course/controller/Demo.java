package com.course.controller;

import io.swagger.annotations.Api;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value="v1",description = "这是第一个版本的demo")
@RequestMapping(value = "v1")
public class Demo {
    @Autowired
    private SqlSessionTemplate template;
    @GetMapping(value = "/getUserCount")
    public  int getUserCount(){
        return template.selectOne("getUserCount");
    }
}

