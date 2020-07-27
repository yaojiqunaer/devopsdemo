package com.trs.devopsdemo.utils;

import java.lang.FunctionalInterface;

/**
 * @Title AnlyFunction
 * @Description: 多个参数的函数声明
 * @Create Date: 2020/7/16 15:55
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
@FunctionalInterface
public interface AnyFunction<A, B, C, R> {
    /**
     * Applies this function to the given argument.
     *
     * @param a the function argument
     * @param b the function argument
     * @param c the function argument
     * @return the function result
     */
    R apply(A a, B b, C c);
}
