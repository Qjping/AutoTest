package com.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;
//Spring 的@EnableScheduling 为我们提供了快速的基于多种规则的任务调度功能
@EnableScheduling
@SpringBootApplication
public class Application {
    private  static ConfigurableApplicationContext context;
    public static void main(String[]args){
        Application.context = SpringApplication.run(Application.class,args);
    }
    //@PreDestroy 注解标注的函数，是在应用结束之前执行
    @PreDestroy
    public void close(){
        Application.context.close();
    }
}
