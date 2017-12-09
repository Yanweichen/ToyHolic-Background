package com.ywc.common.page.handler;

import com.ywc.common.page.contanst.DataBaseEnum;

import java.util.Map;

/**
 * @author yanwe
 *         createTime 2017-07-2017/7/31 14:18
 */
public class HandlerFactory {

    private static DataBaseEnum DATA_BASE_ENUM;

    private static final HandlerFactory HANDLER_FACTORY = new HandlerFactory();

    private HandlerFactory(){

    }

    public static HandlerFactory getInstance(DataBaseEnum dataBaseEnum){
        DATA_BASE_ENUM = dataBaseEnum;
        return HANDLER_FACTORY;
    }

    public void initDateHandler(Class clazz, Map<String,Object> map){
        DateHandler.initDateHandler(clazz,map,DATA_BASE_ENUM);
    }

    public void initOperatorHandler(){
        OperatorHandler.initOperatorHandler(DATA_BASE_ENUM);
    }
}
