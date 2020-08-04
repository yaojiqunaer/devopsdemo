package com.trs.devopsdemo.utils;

import io.restassured.response.Response;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title SoapUtil
 * @Description:
 * @Create Date: 2020/7/28 14:55
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
public class SoapUtil {


    /**
     * 根据请求报文，请求服务地址获取 响应报文
     *
     * @param requestSoap    请求报文
     * @param serviceAddress 响应报文
     * @param charSet        字符集
     * @param contentType    类型
     * @return map封装的 服务器响应参数和返回报文.PS:statusCode :200正常响应。responseSoap：响应报文
     */
    public static Map<String, Object> responseSoap(String requestSoap, String serviceAddress, String charSet,
                                                   String contentType) {
        String responseSoap = "";
        Map<String, Object> resultmap = new HashMap<String, Object>();
        PostMethod postMethod = new PostMethod(serviceAddress);
        byte[] b = new byte[0];
        try {
            b = requestSoap.getBytes(charSet);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        InputStream is = new ByteArrayInputStream(b, 0, b.length);
        RequestEntity re = new InputStreamRequestEntity(is, b.length, contentType);
        postMethod.setRequestEntity(re);
        HttpClient httpClient = new HttpClient();
        int statusCode = 0;
        try {
            statusCode = httpClient.executeMethod(postMethod);
            resultmap.put("statusCode", statusCode);
        } catch (IOException e) {
            throw new RuntimeException("执行http请求失败", e);
        }
        if (statusCode == 200) {
            try {
                responseSoap = postMethod.getResponseBodyAsString();
                resultmap.put("responseSoap", responseSoap);
            } catch (IOException e) {
                throw new RuntimeException("获取请求返回报文失败", e);
            }
        } else {
            throw new RuntimeException("请求失败：" + statusCode);
        }
        return resultmap;
    }

    public static void main(String[] args) {
        StringBuilder soap=new StringBuilder(); //构造请求报文
        soap.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3" +
                ".org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n" +
                "    <soap:Body>\n" +
                "        <greeting  xmlns=\"http://impl.service.soapdemo.trs.com/\">\n" +
                "        \t<hello>张三</hello>\n" +
                "        </greeting>\n" +
                "    </soap:Body>\n" +
                "</soap:Envelope>");
        Map map = new HashMap();
        map.put("Content-Type", "text/xml");
        try {
            Response response=RequestUtil.sendpostWithSoap("http://localhost:9090/greet?wsdl",map,soap.toString());
            System.out.println((response.print()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

//        Map<String, Object> responseSoap = SoapUtil.responseSoap(soap.toString(), "http://localhost:9090/greet" +
//                "?wsdl", "utf-8", "text/xml;charset=utf-8");
//        System.out.println(responseSoap.get("statusCode"));
    }



}
