package demo.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我全部的get方法")
public class MyGet {
    @RequestMapping(value = "/getCookies", method = RequestMethod.GET)
    public String getCookies(HttpServletResponse response) {
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        return "geted cookies successful";
    }

    /*
    要求客户端带cookies访问
    这是一个需要携带cookies信息才能访问get请求
     */
    @GetMapping(value = "get/with/cookies")
    @ApiOperation(value ="这是一个需要携带cookies信息才能访问get请求")
    public String getwithcookies(HttpServletRequest request){
        Cookie [] cookies=request.getCookies();
        if(Objects.isNull(cookies)){
            return "你必须携带cookies请求";
        }
        for(Cookie cookie:cookies){
            if (cookie.getName().equals("login")
                    &&cookie.getValue().equals("true")){
                return "登录成功";
            }
        }
      return "cookies错误";
    }

    /**
     开发一个需要携带参数才能访问的get请求
    第一种 url：key=value&key=value
     **/
    @GetMapping(value = "/get/with/param")
    @ApiOperation(value ="这是一个需要携带参数信息才能访问get请求")
    public Map<String,Integer> getlist2(
            @RequestParam (value = "start",required = false)int start,
            @RequestParam (value = "end",required =false )int end){

        Map<String,Integer>map=new HashMap<>();
        map.put("干脆面",100);
        map.put("衣服",200);
        map.put("鞋子",200);
        return map;
    }

    /**
     * 第二种携带参数访问的请求
     * url:port/get/with/param/10/20
     */
    @GetMapping(value = "/get/with/param/{start}/{end}")
    @ApiOperation(value ="这是一个需要携带参数信息才能访问get请求的第二种实现")
    public Map<String,Integer> getlist(
            @PathVariable(value = "start",required = false)int start,
            @PathVariable (value = "end",required =false )int end){

        Map<String,Integer>map=new HashMap<>();
        map.put("干脆面",100);
        map.put("衣服",200);
        map.put("鞋子",200);
        return map;
    }

}
