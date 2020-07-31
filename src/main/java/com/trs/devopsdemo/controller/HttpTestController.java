package com.trs.devopsdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.trs.devopsdemo.domain.DTO;
import com.trs.devopsdemo.entity.JsonBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public JsonBean httpGetParams(String username,String password){
        return new JsonBean(0,"OK",null);
    }
    /**
     * HttpGet请求Headers参数测试
     */
    @GetMapping("httpGetHeaders")
    public JsonBean httpGetParamsHeaders(String username, String password, HttpServletRequest request){
        return new JsonBean(0,"OK",request.getHeader("X-User-Token"));
    }

    /**
     * HttpPost请求Body参数测试
     */
    @PostMapping("httpPostBody")
    public JsonBean httpPostBody(@RequestBody DTO dto){
        return new JsonBean(0,"OK",dto);
    }


    @GetMapping("getToken")
    public JsonBean getToken(String username,String password){
        return new JsonBean(0,"OK", JSONObject.parse("\"token\":\"asdfghjkl\""));
    }


    @PostMapping("addUser")
    public JsonBean addUser(String token){
        return new JsonBean(0,"OK",JSONObject.parse("\"userId\":\"123\""));
    }

    @GetMapping("delUser")
    public JsonBean delUser(String token,Integer id){
        return new JsonBean(0,"OK",null);
    }






}
