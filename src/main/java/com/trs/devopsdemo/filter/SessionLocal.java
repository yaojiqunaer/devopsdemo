package com.trs.devopsdemo.filter;

import java.util.Map;

public class SessionLocal
{
    private static ThreadLocal<Map<String,String>> local = new ThreadLocal<Map<String,String>>();

    /**
     * 设置用户信息
     * 
     * @param user
     */
    public static void setUser( Map<String,String> user )
    {
        local.set( user );
    }

    /**
     * 获取登录用户信息
     * 
     * @return
     */
    public static Map<String,String> getUser()
    {
        return local.get();
    }
}