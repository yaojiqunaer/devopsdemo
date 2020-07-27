package com.trs.devopsdemo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.trs.devopsdemo.domain.mapper.InterfaceMapper;
import com.trs.devopsdemo.domain.mapper.InterfaceSetMapper;
import com.trs.devopsdemo.domain.model.Interface;
import com.trs.devopsdemo.domain.model.InterfaceSet;
import com.trs.devopsdemo.entity.JsonBean;
import com.trs.devopsdemo.service.InterfaceUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * @Title InterfaceUnitServiceImpl
 * @Description:
 * @Create Date: 2020/7/16 14:37
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
@Service
public class InterfaceUnitServiceImpl implements InterfaceUnitService {

    @Autowired
    private InterfaceMapper interfaceMapper;
    @Autowired
    private InterfaceSetMapper interfaceSetMapper;

    @Transactional
    @Override
    public JsonBean yapiSmart(JSON json) {
        JSONArray sets = JSONArray.parseArray(json.toJSONString());
        //获取到接口的分类信息
        for (int i = 0; i < sets.size(); i++) {
            JSONObject jsonObject = sets.getJSONObject(i);//接口分类信息
            InterfaceSet interfaceSet = InterfaceSet
                    .builder()
                    .id(null)
                    .build();
            interfaceSet.setTitle(jsonObject.getString("name"));
            interfaceSet.setCreatetime(jsonObject.getDate("add_time"));
            interfaceSet.setUpdatetime(jsonObject.getDate("up_time"));
            interfaceSet.setDes(jsonObject.getString("desc"));
            interfaceSet.setStatus(0);//默认启用
            interfaceSetMapper.insert(interfaceSet);//执行添加分组
            //该分类下的全部接口
            JSONArray apis = jsonObject.getJSONArray("list");
            for (int j = 0; j < apis.size(); j++) {
                //接口数据封装
                JSONObject jsonObjectj = apis.getJSONObject(j);
                Interface api = Interface.builder()
                        .path("/")
                        .build();
                api.setMethod(jsonObjectj.getString("method"));
                api.setCreatetime(jsonObjectj.getDate("add_time"));
                api.setUpdatetime(jsonObjectj.getDate("up_time"));
                api.setProjectid(jsonObjectj.getInteger("project_id"));
                api.setTitle(jsonObjectj.getString("title"));
                api.setPath(jsonObjectj.getString("path"));
                api.setStatus(0);
                if ("undone".equals(jsonObjectj.getString("status"))) {
                    api.setStatus(-1);//未完成状态为-1
                }
                api.setListid(1);//分类ID应该来自分类
                api.setUserid(1);//用户ID未设置
                interfaceMapper.insert(api);//执行添加API
            }
        }
        //System.out.println(666);
        return new JsonBean(0, "OK", null);
    }

    @Transactional
    @Override
    public JsonBean swaggerSmart(JSON json) {
        JSONObject sets = JSONObject.parseObject(json.toJSONString());//jsonobject
        String paths = sets.getString("paths");//paths的json结构为"paths":{...}
        Map map = (Map) JSON.parse(paths);
        //遍历
        map.forEach((mapKey, mapValue) -> {
            Interface api = Interface.builder().build();
            api.setPath(mapKey.toString());//path路径
            String methodJson = JSONObject.parseObject(mapValue.toString()).toJSONString();//methd的json结构为"post":{...}
            Map map1 = (Map) JSON.parse(methodJson);
            map1.forEach((method, value) -> {//只有一条数据实际
                api.setMethod((method.toString().toUpperCase()));
                JSONObject jsonObject = JSONObject.parseObject((value.toString()));//post具体的值
                api.setTitle(jsonObject.getString("summary"));
            });
            api.setCreatetime(new Date());
            api.setUpdatetime(new Date());
            interfaceMapper.insert(api);
        });
        return new JsonBean(0, "OK", null);
    }

    @Override
    public JsonBean postmanSmart(JSON json) {
        return null;
    }

    @Override
    public JsonBean yapiNormal(JSON json) {
        return null;
    }

    @Override
    public JsonBean swaggerNormal(JSON json) {
        return null;
    }

    @Override
    public JsonBean postmanNormal(JSON json) {
        return null;
    }
}
