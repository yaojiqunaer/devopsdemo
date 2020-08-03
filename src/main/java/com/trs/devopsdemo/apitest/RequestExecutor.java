package com.trs.devopsdemo.apitest;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.trs.devopsdemo.domain.api.ApiDTO;
import lombok.extern.slf4j.Slf4j;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.config.JsonConfig.jsonConfig;
import static com.jayway.restassured.config.SSLConfig.sslConfig;
import static com.jayway.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL;


@Slf4j
public class RequestExecutor {

    private ApiDTO apiDTO;

    private RequestSpecification requestSpecification;


    public RequestExecutor(ApiDTO apiDTO){
        this.apiDTO = apiDTO;
        requestSpecification = given();
        trustAllHosts();
        applyHeaders();
        applyQueryParameters();
         if (apiDTO.getMethod().equalsIgnoreCase("post")){
            if ("form".equalsIgnoreCase(apiDTO.getReqBodyType())){
                applyFormParam();
            }else if ("json".equalsIgnoreCase(apiDTO.getReqBodyType())){
                applyRawParam();
            }
        }
    }

    //trust all hosts regardless if the SSL certificate is invalid
    private void trustAllHosts(){
        RestAssured.config = RestAssured.config().sslConfig(sslConfig().relaxedHTTPSValidation());
        RestAssured.config =  RestAssured.config().jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL));
        requestSpecification.config(RestAssured.config);

    }

    private void applyHeaders(){
        apiDTO.getReqHeaders().stream()
                .forEach(x->{
                    if (x.getName().equalsIgnoreCase("cookie")){
                        String[] cookies = x.getValue().split(";");
                        for (String s:cookies){
                            String[] c = x.getValue().split("=");
                            requestSpecification.cookie(c[0], c[1]);
                        }
                    }else {
                        requestSpecification.header(new Header(x.getName(), x.getValue()));
                    }
                });
    }

    private void applyQueryParameters(){
        apiDTO.getReqQuery().stream()
                .forEach(x->{
                    requestSpecification.params(x.getName(), x.getValue());
                });
    }

    private void applyFormParam(){
        apiDTO.getReqForm().stream()
                .forEach(
                        x->{
                            log.info(x.getName() + "-------" + x.getValue());
                            requestSpecification = requestSpecification.formParam(x.getName(), x.getValue());
                        }
                );
    }

    private void applyRawParam(){
        requestSpecification = requestSpecification.body(apiDTO.getReqBody());
    }

    /**
     * @description 执行http请求
     * @param 
     * @return 
     */
    public Response executeHttpRequest(){
        Response response = null;
        switch (apiDTO.getMethod().toUpperCase()) {
            case "GET":
                response = requestSpecification.when().get(apiDTO.getPath());
                break;
            case "POST":
                response = requestSpecification.when().post(apiDTO.getPath());
                break;
            case "PATCH":
                response = requestSpecification.when().patch(apiDTO.getPath());
                break;
            case "DELETE":
                response = requestSpecification.when().delete(apiDTO.getPath());
                break;
            case "PUT":
                response = requestSpecification.when().put(apiDTO.getPath());
                break;
            case "OPTIONS":
                response = requestSpecification.when().options(apiDTO.getPath());
                break;
            case "HEAD":
                response = requestSpecification.when().head(apiDTO.getPath());
                break;
            default:
                throw new UnsupportedOperationException(String.format("We cannot perform a request of type %s.", apiDTO.getPath()));
        }
        return response;
    }
}
