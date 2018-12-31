package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class CookiesforGet {
    private String url;
    private ResourceBundle bundle;
    private CookieStore store=null;
    @BeforeClass
    public void beforeTest(){
        //ResourceBundle.getBundle读取resourse的配置文件
        bundle=ResourceBundle.getBundle("application", Locale.CHINA);
        url=bundle.getString("test.url");
        System.out.println(url);
    }
    @Test
    public void testGetCookies() throws IOException {
        String result=null;

        HttpGet get= new HttpGet(this.url+bundle.getString("getCookies.uri"));
        //HttpClient没有获取cookies的方法
        DefaultHttpClient client=new DefaultHttpClient();
        HttpResponse response=client.execute(get);
        result= EntityUtils.toString(response.getEntity(),"utf-8");
        //获取cookies信息
        this.store=client.getCookieStore();
        List<Cookie>cookies=store.getCookies();
        for(Cookie cookie:cookies){
            System.out.println(cookie.getName()+":"+cookie.getValue());
        }

    }

    @Test(dependsOnMethods = "testGetCookies")
    public void getwithCookies() throws IOException {
        String uri=url+bundle.getString("getWithCookies.uri");
        HttpGet get=new HttpGet(uri);
        DefaultHttpClient client=new DefaultHttpClient();
        //设置cookies信息
        client.setCookieStore(this.store);
        HttpResponse response=client.execute(get);
        int statusCode=response.getStatusLine().getStatusCode();
        System.out.println(statusCode);
        if(statusCode==200){
            String result=EntityUtils.toString(response.getEntity());
            System.out.println(result);
        }
    }
}
