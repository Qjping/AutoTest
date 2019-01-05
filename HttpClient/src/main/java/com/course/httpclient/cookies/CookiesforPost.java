package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class CookiesforPost {
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
    public void testPostCookies() throws IOException {
        String result=null;
        String url=this.url+bundle.getString("postCookies.uri");
        HttpPost post= new HttpPost(url);

        //HttpClient没有获取cookies的方法
        DefaultHttpClient client=new DefaultHttpClient();

        //设置请求头信息
        post.setHeader("Content-type","text/html;charset=gbk");

        //添加参数
        JSONObject param=new JSONObject();
        param.put("name","hu");
        param.put("age","18");
        StringEntity entity=new StringEntity(param.toString());
        post.setEntity(entity);

        //获取响应结果
        HttpResponse response=client.execute(post);
        StatusLine statusCode=response.getStatusLine();
        //获取cookies信息
        this.store=client.getCookieStore();
        List<Cookie> cookies=store.getCookies();
        for(Cookie cookie:cookies){
            System.out.println(cookie.getName()+":"+cookie.getValue());
        }
        if(statusCode.getStatusCode()==200){
            result= EntityUtils.toString(response.getEntity());
            System.out.println(result);
        }


    }

    @Test(dependsOnMethods = "testPostCookies")
    public void postwithCookies() throws IOException {
        String url=this.url+bundle.getString("postwithCookies.uri");
        HttpPost post=new HttpPost(url);
        DefaultHttpClient client=new DefaultHttpClient();

        //设置cookies信息
        client.setCookieStore(this.store);
        //设置参数
        JSONObject param=new JSONObject();
        param.put("age","18");
        param.put("name","hu");
        StringEntity stringEntity=new StringEntity(param.toString());
        post.setEntity(stringEntity);

        HttpResponse response=client.execute(post);
        int statusCode=response.getStatusLine().getStatusCode();
        System.out.println(statusCode);
        //返回成功，把结果转换成json格式
        if(statusCode==200){
            String result= EntityUtils.toString(response.getEntity());
            JSONObject res=new JSONObject(result);
            System.out.println(res.get("hu"));
        }
    }
}
