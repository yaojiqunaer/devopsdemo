package com.trs.devopsdemo.service.impl;

import cn.hutool.db.Page;
import com.alibaba.fastjson.JSON;
import com.trs.devopsdemo.domain.mapper.InterfaceMapper;
import com.trs.devopsdemo.domain.mapper.InterfaceSetMapper;
import com.trs.devopsdemo.entity.JsonBean;
import com.trs.devopsdemo.service.InterfaceService;
import com.trs.devopsdemo.service.InterfaceUnitService;
import com.trs.devopsdemo.utils.AnyFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;


/**
 * @ClassName InterfaceServiceImpl
 * @Description: API相关接口实现
 * @Author zhangxiaodong
 * @Date 2020/7/8 15:18
 * @Version V1.0
 */
@Service
public class InterfaceServiceImpl implements InterfaceService {

    /**
     * 使用Map+Lambda代替策略模式
     * String为map的key，用于获取Function函数对象
     * JSON为FUNCTION.apply()方法传入的值
     * JsonBean为函数的返回
     */
    private Map<String, Function<JSON, JsonBean>> map = new HashMap<>();
    //两个参数实现
    private Map<String, BiFunction<Page, JSON, JsonBean>> map2 = new HashMap<>();
    //三个参数
    private Map<String, AnyFunction<Page, JSON, Object, JsonBean>> map3 = new HashMap<>();


    @Autowired
    private InterfaceMapper interfaceMapper;

    //注入逻辑单元
    @Autowired
    private InterfaceUnitService interfaceUnitService;

    @Autowired
    private InterfaceSetMapper interfaceSetMapper;

    /**
     * 初始化 业务逻辑分配Map 其中value 存放的是 lambda表达式
     */
    @PostConstruct
    private void chooseImportLogic() {
        map.put("key_yapi_smart", json -> interfaceUnitService.yapiSmart(json));
        map.put("key_yapi_normal", json -> interfaceUnitService.yapiNormal(json));
        map.put("key_swagger_smart", json -> interfaceUnitService.swaggerSmart(json));
        map.put("key_swagger_normal", json -> interfaceUnitService.swaggerNormal(json));
        map.put("key_postman_smart", json -> interfaceUnitService.postmanSmart(json));
        map.put("key_postman_normal", json -> interfaceUnitService.postmanNormal(json));
    }

    //创建map的key 用于判断执行哪个逻辑
    private String createMapKey(String type, String mode) {
        StringBuffer key = new StringBuffer("key");
        key.append("_").append(type).append("_").append(mode);
        return key.toString();
    }

    /**
     * @param json 读取的json对象
     * @param type json类型: yapi、postman、swagger
     * @param mode 导入模式: normal、smart
     * @return jsonbean [0,"OK",Object]
     * @description 将不同类型的、不同导入模式json解析入库
     */
    @Override
    public JsonBean saveApis(JSON json, String type, String mode) {
        String logicKey = createMapKey(type, mode);
        Function<JSON, JsonBean> function = map.get(logicKey);//根据logickey获取到lambda执行
        if (!Objects.isNull(function)) {
            return function.apply(json);//传入json执行函数 具体在本类的 chooseImportLogic()方法中
        }
        return new JsonBean(-999, "系统异常", null);
    }

}
