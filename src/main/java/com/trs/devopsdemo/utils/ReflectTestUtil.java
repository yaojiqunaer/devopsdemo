package com.trs.devopsdemo.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Title ReflectTestUtil
 * @Description: 通过反射调用测试案例
 * @Create Date: 2020/7/30 15:45
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
public class ReflectTestUtil {
    public static void main(String[] args) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.trs.devopsdemo.utils.TestNGUtil");
            Method method = clazz.getMethod("test", String.class, String.class, String.class, Map.class);
            method.invoke(clazz.newInstance(),"1","2");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


}
