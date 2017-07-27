package com.ywc.common.page;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ywc.common.base.service.BaseService;
import com.ywc.common.page.contanst.DataBaseEnum;
import com.ywc.common.page.contanst.OrderEnum;
import com.ywc.common.page.handler.DateHandler;
import com.ywc.common.page.model.PageParam;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 分页处理类
 *
 * @author yanwe
 *         createTime 2017-07-2017/7/26 11:39
 */
public class PageHandler {

    private static final PageHandler pageHandler = new PageHandler();

    public static PageHandler getInstance(){
        return pageHandler;
    }

    public <T> PageInfo<T> processPage(HttpServletRequest request,Class<T> clazz,BaseService<T> baseService){
        return processPage(request,clazz,null,baseService);
    }

    public <T> PageInfo<T> processPage(HttpServletRequest request,Class<T> clazz,Example example, BaseService<T> baseService){
        PageParam pageParam = new PageParam(request);
        PageHelper.startPage(pageParam.getPageStart(),pageParam.getPageSize());
        List<T> data;
        if (example == null){
            data = baseService.selectByExample(createExample(pageParam,clazz));
        } else {
            processCriteria(pageParam,clazz,example);
            data = baseService.selectByExample(example);
        }
        return new PageInfo<>(data);
    }

    public <T> PageInfo<T> processPage(HttpServletRequest request,Map<String,Object> customParam
            , Function<Map<String,Object>,List<T>> function){
        PageParam pageParam = new PageParam(request);
        Map<String, Object> pageSearchMap = pageParam.getPageSearchMap();
        PageHelper.startPage(pageParam.getPageStart(),pageParam.getPageSize());
        pageSearchMap.putAll(customParam);
        List<T> data = function.apply(pageSearchMap);
        return new PageInfo<>(data);
    }

    private Example createExample(PageParam pageParam,Class clazz){
        Example example = new Example(clazz);
        processCriteria(pageParam, clazz, example);
        return example;
    }

    private void processCriteria(PageParam pageParam, Class clazz, Example example) {
        List<PageParam.PageSearch> customParam = pageParam.getPageSearch();
        if (customParam != null && !customParam.isEmpty()){
            DateHandler.initDateHandler(clazz, pageParam.getPageSearchMap(), DataBaseEnum.MYSQL);
            Example.Criteria criteria = example.createCriteria();
            customParam.stream()
                    .filter(pageSearch -> DateHandler.dateHandle(criteria,pageSearch))
                    .forEach(pageSearch -> {
                        switch (pageSearch.getOperator()){
                            case LIKE:
                                criteria.andLike(pageSearch.getSearchKey(), String.valueOf(pageSearch.getValue()));
                                break;
                            case LE:
                                criteria.andLessThan(pageSearch.getSearchKey(), pageSearch.getValue());
                                break;
                            case GT:
                                criteria.andGreaterThan(pageSearch.getSearchKey(), pageSearch.getValue());
                                break;
                            case NE:
                                criteria.andNotEqualTo(pageSearch.getSearchKey(), pageSearch.getValue());
                                break;
                            default:
                                criteria.andEqualTo(pageSearch.getSearchKey(), pageSearch.getValue());
                                break;
                        }
                    });
        }
        if (pageParam.getPageOrders() != null && !pageParam.getPageOrders().isEmpty()){
            List<PageParam.PageOrder> pageOrders = pageParam.getPageOrders();
            pageOrders.forEach(order -> {
                Example.OrderBy orderBy = example.orderBy(order.getOrderKey());
                if (OrderEnum.DESC.equals(order.getOrder())){
                    orderBy.desc();
                } else {
                    orderBy.asc();
                }
            });
        }
    }

}
