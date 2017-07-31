package com.ywc.common.page.handler;

import com.ywc.common.page.contanst.DataBaseEnum;
import com.ywc.common.page.model.PageParam;
import tk.mybatis.mapper.entity.Example;

import java.util.function.BiFunction;

/**
 * @author yanwe
 *         createTime 2017-07-2017/7/31 13:57
 */
public class OperatorHandler {

    private static ThreadLocal<DataBaseEnum> DATA_BASE_ENUM = ThreadLocal.withInitial(() -> null);

    private static ThreadLocal<BiFunction<Example.Criteria,PageParam.PageSearch,Boolean>> functionThreadLocal =
            ThreadLocal.withInitial(() -> null);

    public static void initOperatorHandler(DataBaseEnum dataBaseEnum){
        DATA_BASE_ENUM.set(dataBaseEnum);
        switch (DATA_BASE_ENUM.get()){
            case MYSQL:
                functionThreadLocal.set(OperatorHandler::mySQLHandle);
                break;
            case ORACLE:
                break;
            default:
                throw new IllegalArgumentException("未知的数据库类型:"+DATA_BASE_ENUM.get());
        }
    }

    public static void propertyHandle(Example.Criteria criteria, PageParam.PageSearch pageSearch){
        if (functionThreadLocal.get().apply(criteria,pageSearch)){
            return;
        }
        switch (pageSearch.getOperator()){
            case LE:
                criteria.andLessThan(pageSearch.getSearchKey(), pageSearch.getValue());
                break;
            case GT:
                criteria.andGreaterThan(pageSearch.getSearchKey(), pageSearch.getValue());
                break;
            case NE:
                criteria.andNotEqualTo(pageSearch.getSearchKey(), pageSearch.getValue());
                break;
        }
    }

    private static boolean mySQLHandle(Example.Criteria criteria, PageParam.PageSearch pageSearch){
        switch (pageSearch.getOperator()){
            case LIKE:
                criteria.andLike(pageSearch.getSearchKey()
                        , "%"+String.valueOf(pageSearch.getValue())+"%");
                return true;
            default:
                return false;
        }
    }
}
