package com.trs.devopsdemo.apitest;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.trs.devopsdemo.domain.usecase.Assertion;
import com.trs.devopsdemo.domain.usecase.ReqData;
import com.trs.devopsdemo.utils.IsANumMatcher;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import uk.co.datumedge.hamcrest.json.SameJSONAs;

import java.util.concurrent.TimeUnit;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;

/**
 * @Title AssertionExecutor
 * @Description: 断言执行器
 * @Create Date: 2020/7/27 18:33
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
public class AssertionExecutor {

    private ReqData reqData;
    private Response res;
    private ValidatableResponse validatableResponse;

    public AssertionExecutor(ReqData reqData, Response res) {
        this.reqData = reqData;
        this.res = res;
        this.validatableResponse = res.then();

    }

    //Integer.parseInt 返回正则
    public Matcher assertionType(Assertion assertion) {
        switch (assertion.getType()) {
            case 0://equals等于的断言
                return Matchers.equalTo(assertion.getValue());
            case 1://contains
                return Matchers.containsString(assertion.getValue());
            case 2://does not equal
                return not(assertion.getValue());
            case 3://is empty
                return isEmptyString();
            case 4://is not empty
                return Matchers.is(not(isEmptyString()));
            case 5://equals(json)
                return SameJSONAs.sameJSONAs(assertion.getValue()).
                        allowingExtraUnexpectedFields().allowingAnyArrayOrdering();
            case 6://does not contains
                return Matchers.not(containsString(assertion.getValue()));
            case 7://is a number
                return IsANumMatcher.generateMatcher();
            case 8://equals(number)
                return Matchers.anyOf(Matchers.equalTo(Double.parseDouble(assertion.getValue())),
                        Matchers.equalTo(Integer.parseInt(assertion.getValue())));
            case 9://less than or equal
                return Matchers.lessThanOrEqualTo(Double.parseDouble(assertion.getValue()));
            case 10://greater than
                return Matchers.greaterThan(Double.parseDouble(assertion.getValue()));
            case 11://greater than or equal
                return Matchers.greaterThanOrEqualTo(Double.parseDouble(assertion.getValue()));
            case 12://has item
                return hasItem(assertion.getValue());
            case 13://json schema
                // Given
                JsonSchemaFactory jsonSchemaFactory =
                        JsonSchemaFactory.newBuilder().setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(SchemaVersion.DRAFTV4).freeze()).freeze();
                return matchesJsonSchema(assertion.getValue()).using(jsonSchemaFactory);
            default:
                return nullValue();
        }
    }

    public void sourceMatch(Assertion assertion) {
        if (res.getStatusCode()!=200){
            //响应码不等于200 直接断言失败
            assertion.setFailedReason("请求响应状态不为200===============/n"+res.asString());
            assertion.setIsSuccess('0');
            return;
        }
        switch (assertion.getSource()) {
            case "Headers":
                break;
            case "json":
                processJsonBody(assertion);
                break;
            case "Response Size(bytes)":
                break;
            case "Status code":
                processStatusCode(assertion);
                break;
            case "Response Time(ms)":
                processResponseTime(assertion);
                break;
            case "XML Body":
                break;
            default:
                break;
        }
    }


    private void processJsonBody(Assertion assertion) {
        try {
            validatableResponse.body(assertion.getName(), assertionType(assertion));//
            assertion.setIsSuccess('1');
        } catch (AssertionError e) {
            assertion.setIsSuccess('0');
            assertion.setFailedReason(e.toString());
        }catch (Exception e){
            //捕获到其他异常
            assertion.setIsSuccess('0');
            assertion.setFailedReason("服务器断言执行器异常");//实际可能前端构造断语句出错
            System.out.println(res.asString());
        }
    }

    private void processStatusCode(Assertion assertion) {
        Double d;
        try {
            d = Double.parseDouble(assertion.getValue());
            if (res.getStatusCode() == d) {
                assertion.setIsSuccess('1');
            } else {
                assertion.setIsSuccess('0');
                assertion.setFailedReason("status code is not equal " + assertion.getValue());
            }
        } catch (AssertionError e) {
            assertion.setIsSuccess('0');
            assertion.setFailedReason(e.toString());
        }
    }

    private void processResponseTime(Assertion assertion) {
        try {
            validatableResponse.time(assertionType(assertion), TimeUnit.MICROSECONDS);
            assertion.setIsSuccess('1');
        } catch (AssertionError e) {
            assertion.setIsSuccess('0');
            assertion.setFailedReason(e.toString());
        }
    }

    public void executeHttpAssert() {
        reqData.getAssertion().stream()
                .forEach(x -> {
                    sourceMatch(x);
                });
    }

    public static void main(String args[]){

        //username=zhangsan的断言
        Assertion assertion = new Assertion("data.user[0].username","zhangsan",0,"",'1',"");
        try {
           // assertThat(20, assertionType(assertion));
        }catch (AssertionError e){

            System.out.println(e.toString());
        }
    }
}
