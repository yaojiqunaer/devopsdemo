package com.trs.devopsdemo.domain.bo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Title JsonNode
 * @Description:
 * @Create Date: 2020/8/18 17:29
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
public class JsonNodeParser {


    public static void main(String[] args) {
        String json = "{\n" +
                "        \"dataType\": 7,\n" +
                "        \"properties\": [\n" +
                "            {\n" +
                "                \"name\": \"code\",\n" +
                "                \"dataType\": 2\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"msg\",\n" +
                "                \"dataType\": 1\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"data\",\n" +
                "                \"dataType\": 8,\n" +
                "                \"items\": {\n" +
                "                    \"dataType\": 7,\n" +
                "                    \"properties\": [\n" +
                "                        {\n" +
                "                            \"name\": \"username\",\n" +
                "                            \"dataType\": 2\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"name\": \"password\",\n" +
                "                            \"dataType\": 2\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"name\": \"language\",\n" +
                "                            \"dataType\": 8,\n" +
                "                            \"items\": {\n" +
                "                                \"dataType\": 2\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    }";

        List<ResBodyField> fields = new ArrayList<>();
        fields = parser(JSONObject.parseObject(json), fields);
        System.out.println(fields.toString());

    }


    public static List<ResBodyField> parser(JSONObject jsonObject, List<ResBodyField> fields) {

        Integer dataType = jsonObject.getInteger("dataType");//获取数据类型
        if (Objects.nonNull(dataType) && dataType == 7) {
            //object类型
            JSONArray properties = jsonObject.getJSONArray("properties");//获取Object下的属性
            for (int i = 0; i < properties.size(); i++) {
                JSONObject property = properties.getJSONObject(i);//单个property
                Integer dataType1 = property.getInteger("dataType");
                String name = property.getString("name");
                System.out.println(name + "===" + dataType1);
                //设置属性名
                ResBodyField field = new ResBodyField();
                field.setParameterName(name);
                field.setDataType(dataType1);

                if (dataType1 == 7 || dataType1 == 8) {
                    field.setChild(new ArrayList<ResBodyField>());
                    parser(property, field.getChild());//设置孩子节点
                }
                fields.add(field);
            }

        }
        if (Objects.nonNull(dataType) && dataType == 8) {
            //array类型
            JSONObject items = jsonObject.getJSONObject("items");
            Integer dataType1 = items.getInteger("dataType");//数组的dataType
            if (dataType1 == 7 || dataType1 == 8) {
                parser(items, fields);
            } else {
                System.out.println("===" + dataType1);
                //设置属性dataType
                ResBodyField field = new ResBodyField();
                field.setDataType(dataType1);
                fields.add(field);
            }
        }
        return fields;
    }


}
