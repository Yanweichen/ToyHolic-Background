package com.ywc.common.page.handler;

import com.alibaba.fastjson.JSONArray;
import com.ywc.common.base.functioninterface.SuFunction;
import com.ywc.common.page.contanst.DataBaseEnum;
import com.ywc.util.Underline2Camel;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static com.ywc.common.page.contanst.PageConstant.BEGIN_TIME;
import static com.ywc.common.page.contanst.PageConstant.DATE_SEARCH;
import static com.ywc.common.page.contanst.PageConstant.END_TIME;


/**
 * 日期处理器
 *
 * @author yanwe
 *         createTime 2017-03-2017/3/10 10:00
 */
public class DateHandler {

    private static ThreadLocal<Object> beginTime = ThreadLocal.withInitial(() -> null);
    private static ThreadLocal<Object> endTime = ThreadLocal.withInitial(() -> null);
    private static ThreadLocal<List<String>> datePropertyNameList = ThreadLocal.withInitial(() -> null);
    private static ThreadLocal<DataBaseEnum> dataBaseEnum = ThreadLocal.withInitial(() -> null);
    private static ThreadLocal<SuFunction<String,Object,Object,String>> betweenDateFunctionThread = ThreadLocal.withInitial(() -> null);
    private static ThreadLocal<BiFunction<String, Object,String>> singleDateFunctionThread = ThreadLocal.withInitial(() -> null);

    /**
     * 获取日期属性名称
     *
     * @param clazz 类
     * @param map 搜索参数
     * @param dateBase 数据库类型
     */
    public static void initDateHandler(Class clazz, Map<String,Object> map,DataBaseEnum dateBase) {
        //初始化数据库类型
        dataBaseEnum.set(dateBase);
        switch (dataBaseEnum.get()){
            case MYSQL:
                betweenDateFunctionThread.set(DateHandler::MySQLBetweenDateHandle);
                singleDateFunctionThread.set(DateHandler::MySQLSingleDateHandle);
                break;
            case ORACLE:
                break;
            default:
                throw new IllegalArgumentException("未知的数据库类型:"+dataBaseEnum.get());
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            //指定了日期字段
            if(DATE_SEARCH.equals(entry.getKey())){
                if(entry.getValue() == null) {
                    throw new IllegalArgumentException("日期搜索参数错误！");
                }
                JSONArray propertyList = JSONArray.parseArray(entry.getValue().toString());
                List<String> stringList = propertyList.stream().map(Object::toString).collect(Collectors.toList());
                datePropertyNameList.set(stringList);
                map.remove(DATE_SEARCH);
                return;
            }
        }
        //未指定自动获取日期字段
        List<String> stringList = Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> Date.class.equals(field.getType()))
                .map(Field::getName)
                .collect(Collectors.toList());
        datePropertyNameList.set(stringList);
    }

    /**
     * 处理日期类型搜索
     *
     * @param criteria 组装条件对象
     * @param entry 搜索条件
     * @return 是否已经处理了日期类型
     */
    public static boolean dateHandle(Example.Criteria criteria, Map.Entry<String, Object> entry) {
        SuFunction<String,Object,Object,String> betweenDateFunction = betweenDateFunctionThread.get();
        BiFunction<String, Object,String> singleDateFunction = singleDateFunctionThread.get();
        if(BEGIN_TIME.equals(entry.getKey())){
            beginTime.set(entry.getValue());
            if(endTime.get() != null){
                doBetweenHandle(criteria, betweenDateFunction);
            }
            return false;
        }
        if(END_TIME.equals(entry.getKey())){
            endTime.set(entry.getValue());
            if(beginTime.get() != null){
                doBetweenHandle(criteria, betweenDateFunction);
            }
            return false;
        }
        //单日期搜索
        for (String datePropertyName : datePropertyNameList.get()) {
            if(datePropertyName.equals(entry.getKey())){
                criteria.andCondition(singleDateFunction.apply(entry.getKey(),entry.getValue()));
                return false;
            }
        }
        return true;
    }

    private static void doBetweenHandle(Example.Criteria criteria, SuFunction<String, Object, Object, String> betweenDateFunction) {
        if(datePropertyNameList.get() == null || datePropertyNameList.get().isEmpty()){
            throw new RuntimeException("未找到日期类型属性！");
        }
        //支持多字段日期排序
        for (String datePropertyName : datePropertyNameList.get()) {
            String sql = betweenDateFunction.apply(datePropertyName, beginTime.get(), endTime.get());
            criteria.andCondition(sql);
        }
    }

    /**
     * MySQL 类型单日期处理
     *
     * @param property 属性名
     * @param date 时间
     * @return SQL
     */
    private static String MySQLSingleDateHandle(String property, Object date){
        return "DATE_FORMAT("+
                Underline2Camel.camel2Underline(property)+",'%Y-%m-%d') " +
                "= DATE_FORMAT(STR_TO_DATE('"+date+"', '%Y-%m-%d %H:%i:%s'),'%Y-%m-%d')";
    }

    /**
     * MySQL 日期区间处理
     *
     * @param property 属性名
     * @param beginTime 起始时间
     * @param endTime 结束时间
     * @return SQL
     */
    private static String MySQLBetweenDateHandle(String property, Object beginTime,Object endTime){
        return "DATE_FORMAT("+ Underline2Camel.camel2Underline(property)+ ",'%Y-%m-%d')" +
                " BETWEEN " +
                "DATE_FORMAT(STR_TO_DATE('"+beginTime+"','%Y-%m-%d %H:%i:%s'),'%Y-%m-%d')" +
                " AND " +
                "DATE_FORMAT(STR_TO_DATE('"+endTime+"','%Y-%m-%d %H:%i:%s'),'%Y-%m-%d')";
    }

}
