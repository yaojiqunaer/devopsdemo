package com.trs.devopsdemo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.JsonPath;
import com.trs.devopsdemo.apitest.AssertionExecutor;
import com.trs.devopsdemo.apitest.RequestExecutor;
import com.trs.devopsdemo.domain.api.ApiDTO;
import com.trs.devopsdemo.domain.api.Form;
import com.trs.devopsdemo.domain.api.Header;
import com.trs.devopsdemo.domain.api.Query;
import com.trs.devopsdemo.domain.bo.JsonNodeParser;
import com.trs.devopsdemo.domain.bo.ResBodyField;
import com.trs.devopsdemo.domain.dto.ApiGroupDto;
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
import com.trs.devopsdemo.test.TestSeesion;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
    public BaseResult createApiGroup(@RequestBody ApiGroupDto group) {
        //
        return BaseResult.success();
    }

    /**
     * @param groupId 接口分组ID
     * @return
     * @description 删除接口分组
     */
    @GetMapping("apiManagement/deleteGroup")
    public BaseResult deleteApiGroup(@RequestParam Integer groupId) {
        return BaseResult.success();
    }


    /**
     * @param
     * @return
     * @description 查询分组信息
     */
    @GetMapping("apiManagement/selectGroups")
    public BaseResult selectGroups(String keyWord) {
        log.info("查询接口分组：{}", "");
        return null;
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


    @PostMapping("case/parseResponseTest")
    public BaseResult parseResponseTest(@RequestBody String apiDTO) {
        System.out.println(apiDTO);
        JSONObject jsonObject = JSONObject.parseObject(apiDTO);
        JSONObject resBody = jsonObject.getJSONObject("resBody");
        List<ResBodyField> fields = new ArrayList<>();
        fields = JsonNodeParser.parser(resBody, fields);

        return BaseResult.success(fields);
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

    @PostMapping("pppp")
    public void getsss() {
        TestSeesion.get();
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
//
//    @PostMapping("executeInterface")
//    public JsonBean executeInterface(@RequestBody ApiDTO apiDTO) {
//        RequestExecutor executor = new RequestExecutor(apiDTO);
//        Long t1 = System.currentTimeMillis();
//
//        Response response = executor.executeHttpRequest();//执行
//        log.info("返回的值为{}", response.body().asString());
//        List<Assertion> assertions = apiDTO.getAssertions();
//        //断言
//        if (Objects.nonNull(apiAssertions)) {
//            try {
//                if ("等于".equals(apiAssertions.getType())) {
//                    String s = apiAssertions.getName().split("$.")[0];
//                    response.then().body(s, equalTo("你好"));
//                }
//                if ("包含".equals(apiAssertions.getType())) {
//
//                }
//            } catch (AssertionError e) {
//                log.info("==========================={}", "断言失败");
//            }
//        }
//        return new JsonBean(0, "OK", response.asString() + "=======" + (System.currentTimeMillis() - t1));
//    }


    @PostMapping("executeUsecase")
    public JsonBean executeUsecase(@RequestBody UsecaseDTO usecaseDTO) {
        long l1 = System.currentTimeMillis();
        excuteUsecase(usecaseDTO);
        return new JsonBean(0, "OK", System.currentTimeMillis() - l1);

    }

    /**
     * @param usecases
     * @return
     * @description 多线程运行用例集
     */
    @PostMapping("runCollectionWithThread")
    public JsonBean runCollectionWithThread(@RequestBody List<UsecaseDTO> usecases) {//模拟执行一个集合20个用例
        long l1 = System.currentTimeMillis();
        AtomicInteger index = new AtomicInteger(1);
        ExecutorService executorService = Executors.newFixedThreadPool(5);//定长线程池
        usecases.stream().forEach(usecase -> {//五个线程
            executorService.execute(() -> {
                excuteUsecase(usecase);
                log.info("当前线程名{},线程id{},用例{}执行完毕", Thread.currentThread().getName(), Thread.currentThread().getId(),
                        index.getAndIncrement());
            });
        });
        executorService.shutdown();//停止接受新任务 原来的线程继续
        try {
            executorService.awaitTermination(5, TimeUnit.MINUTES);//线程终止或者到了5分钟计算时长
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new JsonBean(0, "OK", System.currentTimeMillis() - l1);
    }

    /**
     * @param usecases
     * @return
     * @description 同步运行用例集
     */
    @PostMapping("runCollectionWithSyn")
    public JsonBean runCollectionWithSyn(@RequestBody List<UsecaseDTO> usecases) {
        long l1 = System.currentTimeMillis();
        usecases.forEach(this::excuteUsecase);
        return new JsonBean(0, "OK", System.currentTimeMillis() - l1);
    }

    private void excuteUsecase(UsecaseDTO usecaseDTO) {
        Map<String, String> reqDataCache = Collections.synchronizedMap(new HashMap<>());
        AtomicInteger index = new AtomicInteger(0);//自增索引
        usecaseDTO.getRequests().forEach(request -> {
            ApiDTO apiDTO = getApiDTOById(request.getApiId());//接口信息
            List<ReqData> reqData = request.getData();
            reqData.forEach(data -> {
                index.getAndIncrement();
                //处理query数据 判断是否包含接口返回
                List<Query> querys = data.getQuery();
                if (Objects.nonNull(querys) && querys.size() != 0) {
                    //CollectionUtils.isEmpty()
                    querys = querys.stream().map(x -> {
                        if (x.getType() == 1) {//处理接口返回数据 暂时从static中获取
                            String jsonPath = x.getValue();//例：$.uuid.data.token
                            String uuid = jsonPath.split("\\.")[1];
                            String resBody = reqDataCache.get(uuid);//根据uuid获取
                            String read = Objects.toString(JsonPath.parse(resBody).read(jsonPath.replaceAll(uuid +
                                    ".", "")));//需要替换的值
                            // com.jayway.jsonpath.PathNotFoundException: Missing property in path $['data']
                            x.setValue(read);
                            log.info("接口返回数据{}替换{}", read, jsonPath);
                            return x;
                        }
                        return x;
                    }).collect(Collectors.toList());
                }
                //处理header数据 判断是否包含接口返回
                List<Header> headers = data.getHeader();
                if (Objects.nonNull(headers) && headers.size() != 0) {
                    headers = headers.stream().map(x -> {
                        if (x.getType() == 1) {//处理接口返回数据 暂时从static中获取
                            String jsonPath = x.getValue();//例：$.uuid.data.token
                            String uuid = jsonPath.split("\\.")[1];
                            String resBody = reqDataCache.get(uuid);//根据uuid获取
                            String read = JsonPath.parse(resBody).read(jsonPath.replaceAll(uuid + ".", ""));//需要替换的值
                            x.setValue(read);
                            log.info("接口返回数据{}替换{}", read, jsonPath);
                            return x;
                        }
                        return x;
                    }).collect(Collectors.toList());
                }

                //处理body数据 判断是否包含接口返回
                ReqBody body = data.getBody();
                if (Objects.nonNull(body.getType()) && body.getType() == 0) {
                    //常量
                }


                List<Form> forms = data.getForm();
                if (Objects.nonNull(forms) && forms.size() != 0) {
                    forms = forms.stream().map(y -> {
                        if (y.getType() == 1) {//处理接口返回数据 暂时从static中获取
                            String jsonPath = y.getValue();//例：$.uuid.data.token
                            String uuid = jsonPath.split("\\.")[1];
                            String resBody = reqDataCache.get(uuid);//根据uuid获取
                            String read = JsonPath.parse(resBody).read(jsonPath.replaceAll(uuid + ".", ""));//需要替换的值
                            y.setValue(read);
                            log.info("接口返回数据{}替换{}", read, jsonPath);
                            return y;
                        }
                        return y;
                    }).collect(Collectors.toList());
                }

                apiDTO.setReqQuery(querys);
                apiDTO.setReqBody(body.getValue());
                apiDTO.setReqHeaders(headers);
                apiDTO.setReqForm(forms);

                //执行当前数据请求
                RequestExecutor requestExecutor = new RequestExecutor(apiDTO);
                try {
                    Response response = requestExecutor.executeHttpRequest();
                    String resBody = response.asString();
                    log.info("请求{}执行完毕 响应结果{}", index, resBody);
                    reqDataCache.put(data.getUuid(), resBody);

                    //响应正常 断言
                    List<Assertion> assertions = data.getAssertion();
                    if (assertions != null && assertions.size() != 0) {
                        //断言执行器
                        AssertionExecutor assertionExecutor = new AssertionExecutor(data, response);
                        assertionExecutor.executeHttpAssert();
                        assertions.forEach(x -> {
                            if (x.getIsSuccess() == '1') {
                                //成功
                                log.info("断言结果{}===={},", "成功", x.getName());
                            } else {
                                log.info("断言结果{}===={},", "失败", x.getFailedReason());
                            }

                        });
                    }
                } catch (UnsupportedOperationException e) {

                }

            });

        });
    }


    //private static Map<String, String> reqDataCache = Collections.synchronizedMap(new HashMap<>());


    private ApiDTO getApiDTOById(Long id) {
        ApiDTO apiDTO = new ApiDTO();
        ArrayList<Form> form = new ArrayList<>();
        List<Header> headers = new ArrayList<>();
        List<Query> queries = new ArrayList<>();
        if (id == 3) {
            //模拟查询到id为3的接口信息 login接口
            apiDTO.setApiId(id);
            apiDTO.setMethod("POST");
            apiDTO.setName("登录接口");
            apiDTO.setPath("http://localhost:8080/devops/test/login");
            apiDTO.setReqBodyType("form");

        }
        if (id == 7) {
            //模拟查询到id为7的接口信息 查询用户接口
            apiDTO.setApiId(id);
            apiDTO.setMethod("GET");
            apiDTO.setName("查询用户信息");
            apiDTO.setPath("http://localhost:8080/devops/test/getUsers");
            apiDTO.setResBody("");
        }

        if (id == 10) {
            //模拟查询到id为10的接口信息 添加用户接口
            apiDTO.setApiId(id);
            apiDTO.setMethod("POST");
            apiDTO.setName("添加用户信息");
            apiDTO.setPath("http://localhost:8080/devops/test/addUser");
            apiDTO.setReqBodyType("json");
        }
        if (id == 15) {
            //模拟查询到id为15的接口信息 删除用户接口
            apiDTO.setApiId(id);
            apiDTO.setMethod("GET");
            apiDTO.setName("删除用户信息");
            apiDTO.setPath("http://localhost:8080/devops/test/deleteUser");
        }
        if (id == 16) {
            apiDTO.setApiId(id);
            apiDTO.setMethod("POST");
            apiDTO.setName("网关登录");
            apiDTO.setReqBodyType("json");
            apiDTO.setPath("http://apolloweb.devdemo.trs.net.cn/apollo/user/login");
        }
        return apiDTO;
    }

    private Map putKeyValue(Map map, String key, String value) {
        if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value)) {
            map.put(key, value);
        }
        return map;
    }

    public static void main(String[] args) {


        String jsonPath = "$.123456.data.token";//例：$.uuid.data.token
        String uuid = jsonPath.split("\\.")[1];
        String resBody = "{\n" +
                "            \"code\":1,\n" +
                "            \"data\":{\n" +
                "                \"token\":\"2132\"\n" +
                "            }\n" +
                "        }";//根据uuid获取
        String read = JsonPath.parse(resBody).read(jsonPath.replaceAll(uuid + ".", ""));//替换的值
        System.out.println(read);


//        String s = "$.123456.data.token";
//        String uuid = s.split("\\.")[1];
//        System.out.println(uuid);
//        System.out.println(s.replaceAll(uuid + ".", ""));
    }
//
//    public static void main(String[] args) {
//        String s="$.data.title";
//        System.out.println(s.replaceAll("$.",""));
//    }


//    public static void main(String[] args) {
//        List<String> list = new ArrayList<String>();
//        list.add("a");
//        list.add("b");
//        list.add("c");
//        //用map来实现多重条件过滤
//        List<String> collect = list.stream().map((x) -> {
//            if (x.contains("a")) {
//                return x.replace("a", "1");
//            }
//            if (x.contains("b")) {
//                return x.replace("b", "2");
//            }
//            return x;
//        }).collect(Collectors.toList());
//        collect.forEach(System.out::println);
//
//
//    }
}
