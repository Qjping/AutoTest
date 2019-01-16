package demo.server;

import demo.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description = "这是我全部的get请求")
@RequestMapping("v1")
public class MyPost {
    //这个是用来装我们cookies信息
    private static Cookie cookies;

    //用户登录成功获取到cookies，然后访问其他接口获取到的列表
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录接口，成功后获取cookies",httpMethod = "post")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "username",required = true)String name,
                        @RequestParam(value = "pwd",required = true)String pwd){
        if(name.equals("hu")&&pwd.equals("123456")){
            cookies=new Cookie("login","true");
            response.addCookie(cookies);
            return "login sucesssful";
        }else {
            return "lohin faied";
        }
    }

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    public String getList(HttpServletRequest request,
                          @RequestBody User user){
         Cookie[]cookie =request.getCookies();
        for(Cookie cookie1:cookie){
            if(cookie1.getName().equals("login")&&
            cookie1.getValue().equals("true")){
                user=new User();
                user.setAge("10");
                user.setName("jack");
                user.setPassword("123456");
                user.setSex("boy");
                user.setUserName("qiu");
                return user.toString();
            }
        }
        return "cookies验证失败";

    }
}
