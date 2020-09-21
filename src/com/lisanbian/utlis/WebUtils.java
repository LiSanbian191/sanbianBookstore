package com.lisanbian.utlis;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtils {
    //把map中的值注入到bean中,并使用泛型优化
    public static <T> T copyParametersToBean(Map value, T bean){
        try {
            BeanUtils.populate(bean,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

    //书籍编号处理
    public static int parseInt(String id,int defaultValue){
        try {
            return Integer.parseInt(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return defaultValue;
    }
}
