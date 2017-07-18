package com.ywc.util;

import com.ywc.common.context.SpringContext;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * @author yanwe
 *         createTime 2017-07-2017/7/17 15:48
 */
public class SpringBeanUtil {

    private static ApplicationContext applicationContext = SpringContext.getApplicationContext();

    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

    public static <T> Map<String,T> getBeanOfType(Class<T> clazz){
        return applicationContext.getBeansOfType(clazz);
    }
}
