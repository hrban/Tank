package com.yaoshuai.tank.tank9_1;

import java.io.IOException;
import java.util.Properties;

/*
*
* 管理配置文件
*
* 根据配置文件的key读取配置文件中value值
*
* */
public class PropertyMgr {
    static Properties properties = new Properties();
    static {
        try {
            properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Object get(String key){
        if(properties == null) return  null;
        return properties.get(key);
    }
    public static int getInt(String key){
        if(properties == null) return 0;
        return Integer.valueOf((String)properties.get(key));

    }

}
