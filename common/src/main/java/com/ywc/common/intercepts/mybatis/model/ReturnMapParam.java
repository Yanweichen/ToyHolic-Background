package com.ywc.common.intercepts.mybatis.model;

import java.util.HashMap;

/**
 * MyBatis拦截器返回Map的参数
 * @author yanweicheng
 */
public class ReturnMapParam extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;  
  
    /** 
     * 作为Key的字段对应MapParam的Key 
     */  
    public static final String  KEY_FIELD = "mapKeyField";  
    /** 
     * 作为Value的字段对应MapParam的Key 
     */  
    public static final String VALUE_FIELD = "mapValueField";  
      
    private ReturnMapParam(){}
      
    /** 
     * 指定keyField和valueField 
     * @param keyField Map中key对应的字段 
     * @param valueField Map中value对应的字段 
     */  
    public ReturnMapParam(String keyField, String valueField) {
        this.put(KEY_FIELD, keyField);  
        this.put(VALUE_FIELD, valueField);  
    }  
      
} 