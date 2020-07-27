### Rest-Assured测试框架验证

#### Rest-Assured简介

Rest-Assured 是一个测试 RESTful Web Services 的 Java 类库。可以使用 Rest-Assured 编写高度自定义化的 HTTP 请求用来测试各种各样 Restful 服务组合的业务实现。

Rest-Assured 同样能够验证从服务器返回的 HTTP 响应报文，例如服务器响应状态码，响应报文内容等，Rest-Assured 可以灵活的用来进行 Restful Webservice 测试。

#### Http协议请求测试 

##### 模拟测试的接口（返回JSON）

```java
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
```

##### GET请求

- **无参数测试**

  **待测接口信息**

  ```json
  {
      "路径":"127.0.0.1:8080/devops/test/httpGet",
      "请求方式":"GET",
      "返回体":"json格式"
  }
  ```

  ![image-20200727143317132](C:\Users\zhangxiaodong\AppData\Roaming\Typora\typora-user-images\image-20200727143317132.png)

  **正常测试返回**

  ```wiki
  HTTP/1.1 200 
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Mon, 27 Jul 2020 06:38:43 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive
  
  {
      "code": 0,
      "msg": "OK",
      "data": null
  }
  ```

  **服务器连接异常**

  ```wiki
  java.net.ConnectException: Connection refused: connect
  ```

  **路径不存在返回**

  ```wiki
  HTTP/1.1 404 
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Mon, 27 Jul 2020 06:41:17 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive
  
  {
      "timestamp": "2020-07-27T06:41:17.463+0000",
      "status": 404,
      "error": "Not Found",
      "message": "No message available",
      "path": "/devops/test/httpXXX"
  }
  ```

- **Query参数测试**

  **待测接口信息**

  ```json
  {
      "路径":"127.0.0.1:8080/devops/test/httpGetParams",
      "请求方式":"GET",
      "返回体":"json格式",
      "请求参数":"username=zs,password=123"
  }
  ```

  ![image-20200727145310074](C:\Users\zhangxiaodong\AppData\Roaming\Typora\typora-user-images\image-20200727145310074.png)

  **测试结果**

  ```
  可行
  ```

- **请求头参数测试**

  **待测接口信息**

  ```json
  {
      "路径":"127.0.0.1:8080/devops/test/httpGetHeaders",
      "请求方式":"GET",
      "返回体":"json格式",
      "请求头":"X-User-Token=zs,Content-Type=application/json",
  }
  ```

  **测试结果**

  ```wiki
  Request method:	GET
  Request URI:	http://127.0.0.1:8080/devops/test/httpGetHeaders?password=123456&username=zs
  Query params:	password=123456
  				username=zs
  Headers:		X-User-Token=dw1q123da3sa4x
  				Accept=*/*
  				Content-Type=application/json; charset=UTF-8
  {
      "code": 0,
      "msg": "OK",
      "data": null
  }
  ```

##### POST请求

- **请求Body测试**

  **接口信息**

  ```json
  {
      "路径":"127.0.0.1:8080/devops/test/httpPostBody",
      "请求方式":"POST",
      "返回体":"json格式",
      "请求Body":"{"title":"hello","num":"1"}"
  }
  ```

  **返回信息**

  ```wiki
  Request method:	POST
  Request URI:	http://127.0.0.1:8080/devops/test/httpPostBody
  Proxy:			<none>
  Request params:	<none>
  Query params:	<none>
  Form params:	<none>
  Path params:	<none>
  Headers:		X-User-Token=dw232dewf3rg34g
  				Accept=*/*
  				Content-Type=application/json; charset=UTF-8
  Cookies:		<none>
  Multiparts:		<none>
  Body:
  {
      "title": "hello",
      "num": "1"
  }
  {
      "code": 0,
      "msg": "OK",
      "data": {
          "title": "hello",
          "num": 1
      }
  }
  
  ```

  