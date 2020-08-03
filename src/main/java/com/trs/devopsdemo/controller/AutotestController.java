package com.trs.devopsdemo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.trs.devopsdemo.apitest.RequestExecutor;
import com.trs.devopsdemo.domain.api.ApiDTO;
import com.trs.devopsdemo.domain.api.Form;
import com.trs.devopsdemo.domain.api.Header;
import com.trs.devopsdemo.domain.api.Query;
import com.trs.devopsdemo.domain.model.DevopsAutotestApiGroup;
import com.trs.devopsdemo.domain.page.Page;
import com.trs.devopsdemo.domain.usecase.Assertion;
import com.trs.devopsdemo.domain.usecase.ReqBody;
import com.trs.devopsdemo.domain.usecase.ReqData;
import com.trs.devopsdemo.domain.usecase.UsecaseDTO;
import com.trs.devopsdemo.entity.JsonBean;
import com.trs.devopsdemo.filter.SessionLocal;
import com.trs.devopsdemo.service.DevopsAutotestApiManagementService;
import com.trs.devopsdemo.service.InterfaceService;
import com.trs.devopsdemo.utils.FileUtil;
import com.trs.devopsdemo.utils.RequestUtil;
import com.trs.midend.result.BaseResult;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

import static org.hamcrest.Matchers.equalTo;

/**
 * @Title AutotestController
 * @Description:
 * @Create Date: 2020/7/20 16:27
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
@Slf4j
@RestController
@RequestMapping("autotest")
public class AutotestController {


    @Autowired
    private InterfaceService interfaceService;
    @Autowired
    private DevopsAutotestApiManagementService devopsAutotestApiManagementService;


    @GetMapping("test")
    public JsonBean test() {
        return new JsonBean(0, "OK", null);
    }


    /**
     * @param group 接口分组信息
     * @return
     * @description 新增接口分组
     */
    @PostMapping("apiManagement/createGroup")
    public BaseResult createApiGroup(@RequestBody DevopsAutotestApiGroup group) {
        log.info("新增接口分组：{}", JSONObject.toJSON(group).toString());
        return devopsAutotestApiManagementService.insertApiGroup(group, SessionLocal.getUser());
    }

    /**
     * @param id 接口分组ID
     * @return
     * @description 删除接口分组
     */
    @GetMapping("apiManagement/deleteGroup")
    public BaseResult deleteApiGroup(@RequestParam(required = true) Integer id) {
        log.info("删除接口分组：{}", id);
        return devopsAutotestApiManagementService.deleteApiGroupByGroupId(id, SessionLocal.getUser());
    }


    /**
     * @param
     * @return
     * @description 查询分组信息
     */
    @GetMapping("apiManagement/selectGroups")
    public BaseResult selectGroups(String keyWord) {
        log.info("查询接口分组：{}", "");
        return devopsAutotestApiManagementService.selectGroupsByUserId(1L);
    }

    /**
     * @param groupId
     * @return
     * @description 查询一个分组具体信息
     */
    @GetMapping("apiManagement/selectGroupById")
    public BaseResult selectGroupById(@RequestParam Integer groupId) {

        return null;
    }

    /**
     * @param group
     * @return
     * @description 修改接口分组
     */
    @PostMapping("apiManagement/updateGroup")
    public BaseResult updateApiGroup(@RequestBody DevopsAutotestApiGroup group) {
        log.info("修改接口分组：{}", JSONObject.toJSON(group).toString());
        return devopsAutotestApiManagementService.updateApiGroup(group, SessionLocal.getUser());
    }


    /**
     * @param apiDto
     * @return
     * @description 创建接口
     */
    @PostMapping("apiManagement/createApi")
    public BaseResult createApi(@RequestBody ApiDTO apiDto) {

        System.out.println(apiDto);
        return null;
    }


    /**
     * @param type
     * @param merge
     * @param file
     * @return
     * @description 本地导入接口
     */
    @PostMapping("apiManagement/importApi")
    public JsonBean importApi(@RequestParam String type, @RequestParam String merge, @RequestParam MultipartFile file) {
        if (Objects.isNull(type) || StringUtils.isEmpty(type)) {
            return new JsonBean(-999, "未指定数据导入类型", null);
        }
        if (file.isEmpty()) {
            return new JsonBean(-888, "请上传文件", null);
        }
        JSON apiJson = null;//文件中的json数据
        try {
            apiJson = (JSON) JSON.parse(FileUtil.readFromStream(file.getInputStream()));
            //return new JsonBean(0,"OK",apiJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return interfaceService.saveApis(apiJson, type, merge);
    }


    /**
     * @param
     * @return
     * @description 删除接口
     */
    @PostMapping("apiManagement/deleteApi")
    public BaseResult deleteApisByIds(@RequestBody List<Long> ids) {
        Map<String, String> user = SessionLocal.getUser();
        return null;
    }

    /**
     * @param apiId
     * @return
     * @description 根据API的ID查询接口
     */
    @GetMapping("apiManagement/selectApiById")
    public BaseResult selectApiById(@RequestParam Long apiId) {
        return null;
    }


    /**
     * @param page
     * @return
     * @description 查询分组下的所有接口，分页
     */
    @GetMapping("apiManagement/searchApisByGoupId")
    public BaseResult searchApisByGoupId(Long groupId, Page page) {
        Map<String, String> user = SessionLocal.getUser();
        page.setUserId(Long.parseLong(user.get("userId")));
        return null;
    }


    /**
     * @param
     * @return
     * @description 新增用例集
     */
    @PostMapping("usecase/createUsecaseSet")
    public BaseResult createUsecaseSet() {
        return null;
    }


    /**
     * @param usecaseSetId
     * @return
     * @description 创建用例集副本
     */
    @GetMapping("usecase/copyUsecaseSet")
    public BaseResult copyUsecaseSet(@RequestParam Long usecaseSetId) {
        return null;
    }


    /**
     * @param usecaseSetId
     * @return
     * @description 删除用例集
     */
    @GetMapping("usecase/deleteUsecaseSet")
    public BaseResult deleteUsecaseSet(@RequestParam Long usecaseSetId) {
        return null;
    }


    /**
     * @param
     * @return
     * @description 新增用例集
     */
    @PostMapping("usecase/updateUsecaseSet")
    public BaseResult updateUsecaseSet() {
        return null;
    }


    /**
     * @param ids
     * @return
     * @description 删除用例
     */
    @PostMapping("usecase/deleteUsecase")
    public BaseResult deleteUsecase(@RequestBody List<Long> ids) {
        return null;
    }


    @PostMapping("test")
    public BaseResult test(String body, String url) {
        Long start = System.currentTimeMillis();
        body = "{\n" +
                "    \"groupName\":\"阿波罗devops测试环境\",\n" +
                "    \"basepath\":\"127.0.0.1/gov\"\n" +
                "}";
        Map headers = new HashMap();
        headers.put("X-User-Token", "XXXXX");
        headers.put("Content-Type", "application/json");
        Response response = RestAssured
                .given()
                .headers(headers)
                .body(body)
                .post(url)
                .then().contentType("application/json")
                .extract()
                .response();

        System.out.println("================" + response.getBody().asString());
        System.out.println((System.currentTimeMillis() - start));
        return BaseResult.success();
    }


    @PostMapping("restAssured")
    public JsonBean testRestAssured(@RequestBody Map map) {
        System.out.println(map.get("body").toString());
        String agreement = map.get("agreement").toString();
        String url = agreement + "://" + map.get("url").toString();
        if ("webService".equals(agreement)) {
            url = "http://" + map.get("url").toString();
        }
        String param1Name = map.get("param1-name").toString();
        String param1Value = map.get("param1-value").toString();
        String param2Name = map.get("param2-name").toString();
        String param2Value = map.get("param2-value").toString();
        String param3Name = map.get("param3-name").toString();
        String param3Value = map.get("param3-value").toString();
        String param4Name = map.get("param4-name").toString();
        String param4Value = map.get("param4-value").toString();

        Map params = new HashMap();
        params = this.putKeyValue(params, param1Name, param1Value);
        params = this.putKeyValue(params, param2Name, param2Value);
        params = this.putKeyValue(params, param3Name, param3Value);
        params = this.putKeyValue(params, param4Name, param4Value);

        String header1Name = map.get("header1-name").toString();
        String header1Value = map.get("header1-value").toString();
        String header2Name = map.get("header2-name").toString();
        String header2Value = map.get("header2-value").toString();
        Map headers = new HashMap();
        headers = this.putKeyValue(headers, header1Name, header1Value);
        headers = this.putKeyValue(headers, header2Name, header2Value);


        Response response = null;
        HashMap<Object, Object> resMap = new HashMap<>();

        if ("GET".equals(map.get("method").toString()) && "https".equals(map.get("agreement").toString())) {
            //https get请求
            try {
                response = RequestUtil.sendgetWithHttps(url, headers, params);
                resMap.put("https==========", response.prettyPrint());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        if ("GET".equals(map.get("method").toString()) && "http".equals(map.get("agreement").toString())) {
            //http协议 get请求 无参数
            try {
                response = RequestUtil.sendgetWithHttp(url, params, headers);
                log.warn("连接超时异常");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if ("POST".equals(map.get("method").toString()) && "http".equals(map.get("agreement").toString())) {
            //http协议 get请求 无参数
            try {
                response = RequestUtil.sendpostWithHttp(url, headers, map.get("body").toString());
            } catch (Exception e) {
                e.printStackTrace();
                log.warn("连接超时异常");
            }
        }
        if ("webService".equals(map.get("agreement").toString())) {
            //soap
            try {
                response = RequestUtil.sendpostWithSoap(url, headers, map.get("body").toString());
                resMap.put("xml", response.xmlPath().prettyPrint());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        resMap.put("body", response.getBody().toString());
        resMap.put("headers", response.getHeaders().toString());
        resMap.put("time", response.getTime());
        return new JsonBean(0, "OK", resMap.toString());
    }

    @PostMapping("executeInterface")
    public JsonBean executeInterface(@RequestBody ApiDTO apiDTO) {

        RequestExecutor executor = new RequestExecutor(apiDTO);
        Long t1 = System.currentTimeMillis();
        com.jayway.restassured.response.Response response = executor.executeHttpRequest();//执行
        log.info("返回的值为{}", response.body().asString());
        Assertion apiAssertions = apiDTO.getApiAssertions();
        //断言
        if (!Objects.isNull(apiAssertions)) {
            try {
                if ("等于".equals(apiAssertions.getType())) {
                    String s = apiAssertions.getName().split("$.")[0];
                    response.then().body(s, equalTo("你好"));
                }
                if ("包含".equals(apiAssertions.getType())) {

                }
            } catch (AssertionError e) {
                log.info("==========================={}", "断言失败");
            }
        }
        return new JsonBean(0, "OK", response.asString() + "=======" + (System.currentTimeMillis() - t1));
    }


    @PostMapping("executeUsecase")
    public JsonBean executeUsecase(@RequestBody UsecaseDTO usecaseDTO) {

        usecaseDTO.getRequests().stream().forEach(request -> {

            ApiDTO apiDTO = getApiDTOById(request.getApiId());
            List<ReqData> reqData = request.getData();
            reqData.stream().forEach(data->{
                List<Query> query = data.getQuery();
                Assertion assertion = data.getAssertion();
                ReqBody body = data.getBody();

                apiDTO.


                RequestExecutor requestExecutor=new RequestExecutor(apiDTO);

            });
        });


        return new JsonBean(0, "OK", null);

    }


    private ApiDTO getApiDTOById(Long id) {
        ApiDTO apiDTO = new ApiDTO();
        if ("3".equals(id)) {
            //模拟查询到id为3的接口信息 login接口
            apiDTO.setApiId(id);
            apiDTO.setMethod("POST");
            apiDTO.setName("登录接口");
            apiDTO.setPath("http://localhost:8080/devops/test/login");
            ArrayList<Form> form = new ArrayList<>();
            Form form1 = new Form();
            form1.setName("username");
            form1.setExample("zs");
            Form form2 = new Form();
            form2.setName("password");
            form2.setExample("123");
            form.add(form1);
            form.add(form2);
            apiDTO.setReqForm(form);
            List<Header> headers = new ArrayList<>();
            List<Query> queries = new ArrayList<>();
            apiDTO.setReqHeaders(headers);
            apiDTO.setReqQuery(queries);
        }
        if ("7".equals(id)) {
            //模拟查询到id为7的接口信息 查询用户接口
            apiDTO.setApiId(id);
            apiDTO.setMethod("POST");
            apiDTO.setName("登录接口");
            apiDTO.setPath("http://localhost:8080/devops/test/getUsers");
            ArrayList<Form> form = new ArrayList<>();
            List<Header> headers = new ArrayList<>();
            Query query=new Query();
            List<Query> queries = new ArrayList<>();
            apiDTO.setReqForm(form);
            apiDTO.setReqHeaders(headers);
            apiDTO.setReqQuery(queries);
            apiDTO.setResBody("");
        }
        return apiDTO;
    }

    private Map putKeyValue(Map map, String key, String value) {
        if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value)) {
            map.put(key, value);
        }
        return map;
    }
//
//    public static void main(String[] args) {
//        String s="$.data.title";
//        System.out.println(s.replaceAll("$.",""));
//    }

}
