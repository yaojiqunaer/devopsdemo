package com.trs.devopsdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.trs.devopsdemo.domain.DTO;
import com.trs.devopsdemo.domain.dto.Token;
import com.trs.devopsdemo.domain.dto.User;
import com.trs.devopsdemo.entity.JsonBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title TestController
 * @Description:
 * @Create Date: 2020/7/27 13:33
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
@Slf4j
@RestController
@RequestMapping("test")
public class HttpTestController {


    /**
     * HttpGet请求无参测试
     */
    @GetMapping("httpGet")
    public JsonBean httpGet() {
        return new JsonBean(0, "OK", null);
    }

    /**
     * HttpGet请求Query参数测试
     */
    @GetMapping("httpGetParams")
    public JsonBean httpGetParams(String username, String password) {
        return new JsonBean(0, "OK", null);
    }

    /**
     * HttpGet请求Headers参数测试
     */
    @GetMapping("httpGetHeaders")
    public JsonBean httpGetParamsHeaders(String username, String password, HttpServletRequest request) {
        return new JsonBean(0, "OK", request.getHeader("X-User-Token"));
    }

    /**
     * HttpPost请求Body参数测试
     */
    @PostMapping("httpPostBody")
    public JsonBean httpPostBody(@RequestBody DTO dto, HttpServletRequest request) {
        System.out.println("headers====" + request.getHeader("X-User-Token"));
        System.out.println(dto.toString());
        return new JsonBean(0, "OK", dto);
    }


    @GetMapping("getToken")
    public JsonBean getToken(String username, String password) {
        return new JsonBean(0, "OK", JSONObject.parse("\"token\":\"asdfghjkl\""));
    }


    @PostMapping("addUser")
    public JsonBean addUser(String token) {
        return new JsonBean(0, "OK", JSONObject.parse("\"userId\":\"123\""));
    }

    @GetMapping("delUser")
    public JsonBean delUser(String token, Integer id) {
        return new JsonBean(0, "OK", null);
    }


    //用例测试
    @PostMapping("login")
    public JsonBean login(String username, String password, HttpServletRequest request) {

        System.out.println("login:sessionId"+request.getSession().getId());
        if ("admin".equals(username) && "123456".equals(password)) {
            String token = Token.createTokenByUsernamePassword(username, password);
            request.getSession().setAttribute("token", token);
            return new JsonBean(0, "登录成功", new Token(token));
        }

        return new JsonBean(0, "OK", null);
    }

    @GetMapping("getUsers")
    public JsonBean getUsers(HttpServletRequest request) {
        System.out.println("getUsers:sessionId"+request.getSession().getId());
        System.out.println("header:" + request.getHeader("X-User-Token"));
        System.out.println("session:" + request.getSession().getAttribute("token"));
        if (request.getHeader("X-User-Token").equals(request.getSession().getAttribute("token").toString())) {
            User user1 = new User("张三", "男", 23);
            User user2 = new User("李四", "男", 44);
            User user3 = new User("王五", "女", 50);
            User user4 = new User("赵六", "男", 77);
            User user5 = new User("小七", "女", 18);
            List<User> objects = new ArrayList<>();
            objects.add(user1);
            objects.add(user2);
            objects.add(user3);
            objects.add(user4);
            objects.add(user5);
            return new JsonBean(0, "OK", objects);
        }
        return new JsonBean(-1, "未登录", null);
    }


}
