package com.trs.devopsdemo.domain.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;


/**
 * @Title ReqBody
 * @Description:
 * @Create Date: 2020/7/24 16:54
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
@Data
public class ReqBody {


//    {
//        "type": "object",
//            "properties": {
//        "groupId": {
//            "type": "number",
//                    "description": "分组ID"
//        },
//        "type": {
//            "type": "string",
//                    "description": "接口名称"
//        },
//        "name": {
//            "type": "string",
//                    "description": "接口类型"
//        },
//        "method": {
//            "type": "string",
//                    "description": "请求方式"
//        },
//        "path": {
//            "type": "string",
//                    "description": "接口路径"
//        },
//        "reqBodyType": {
//            "type": "string",
//                    "description": "请求体类型"
//        },
//        "reqBody": {
//            "type": "string",
//                    "description": "请求体json字符串"
//        },
//        "resBody": {
//            "type": "string",
//                    "description": "响应体json字符串"
//        },
//        " reqQuery": {
//            "type": "array",
//                    "items": {
//                "type": "object",
//                        "properties": {
//                    "name": {
//                        "type": "string",
//                                "description": "参数名"
//                    },
//                    "required": {
//                        "type": "string",
//                                "description": "是否必填"
//                    }
//                },
//                "required": [
//                "name",
//                        "required"
//        ]
//            },
//            "description": "请求参数"
//        },
//        "reqHeaders": {
//            "type": "array",
//                    "items": {
//                "type": "object",
//                        "properties": {
//                    "name": {
//                        "type": "string",
//                                "description": "请求头参数名"
//                    },
//                    "value": {
//                        "type": "string",
//                                "description": "请求头参数名"
//                    }
//                },
//                "required": [
//                "name",
//                        "value"
//        ]
//            }
//        }
//    },
//        "required": [
//        "groupId",
//                "type",
//                "name",
//                "method",
//                "path",
//                "reqBodyType",
//                "reqBody",
//                "resBody",
//                " reqQuery",
//                "reqHeaders"
//  ],
//        "description": "接口json"
//    }


    private String type;
    private Map<String,TypeAndDes> properties;
    private List<String> required;
    private String description;
}
