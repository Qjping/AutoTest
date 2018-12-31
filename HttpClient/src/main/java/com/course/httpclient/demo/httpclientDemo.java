package com.course.httpclient.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;

public class httpclientDemo {
    @Test
    public void test1() throws IOException {
        //用来存放我们的结果
        String result;
        HttpGet get=new HttpGet("http://www.baidu.com");
        //这个是用来执行get方法的
        HttpClient client=new DefaultHttpClient();
        HttpResponse response=client.execute(get);
        result= EntityUtils.toString(response.getEntity());
        System.out.println(result);
    }
}
