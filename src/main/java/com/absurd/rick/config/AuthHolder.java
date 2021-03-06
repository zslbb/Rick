package com.absurd.rick.config;

import java.util.List;
import java.util.Map;

/**
 * Created by wangwenwei on 17/6/19.
 */
public class AuthHolder{
    private static final ThreadLocal<Map<String,Object>> MAP_THREAD_LOCAL = new ThreadLocal<>();
    public static void set(Map<String,Object> map){
        MAP_THREAD_LOCAL.set(map);
    }

    public static String username(){
        return  get("username");
    }



    public static String uid(){
        return  get("id");
    }



    public static List<String> roles(){
        return get("authorities");
    }

    private static <T> T get(String key) {
        Map<String, Object> map = getThreadMap();
        if (map != null) {
            return (T) map.get(key);
        }
        return null;
    }

    public static Map<String, Object> getThreadMap() {
        return MAP_THREAD_LOCAL.get();
    }

    public static void clear(){
        Map<String, Object> map = getThreadMap();
        if (map != null) {
            map.clear();
        }
        MAP_THREAD_LOCAL.remove();
    }


}
