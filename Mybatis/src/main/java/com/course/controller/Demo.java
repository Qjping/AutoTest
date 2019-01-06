package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(value="/addUser")
    public int postAddUser(@RequestBody User user){
        return template.insert("addUser",user);
    }

    @PostMapping(value = "/updateUser")
    public int updateUser(@RequestBody User user){
        return template.update("updateUser",user);
    }

    @GetMapping(value = "/delUser/{id}")
    public int delUser(@RequestParam int id){
        if(id<0){
            return -1;
        }
        return  template.delete("delUser",id);
    }
}

