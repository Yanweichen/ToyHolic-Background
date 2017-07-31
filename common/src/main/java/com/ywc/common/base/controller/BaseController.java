package com.ywc.common.base.controller;

import com.ywc.common.base.mapper.BaseMapper;
import com.ywc.common.base.service.BaseService;
import com.ywc.common.page.model.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author yanwe
 *         createTime 2017-07-2017/7/27 14:50
 */
public class BaseController<T> {

    @Autowired
    private BaseService<T> thisService;

    @Autowired
    private BaseMapper<T> thisMapper;

    /**
     * 默认单表分页
     *
     * @return 分页数据
     */
    protected PageData<T> doPage(){
        return new PageData<>(getRequest(),getBeanClazz(), thisMapper);
    }

    /**
     * 自定义条件单表分页
     *
     * @param example 条件
     * @return 分页数据
     */
    protected PageData<T> doPage(Example example){
        return new PageData<>(getRequest(),getBeanClazz(),example, thisMapper);
    }

    /**
     * 自定义方法分页
     *
     * @param function 方法
     * @return 分页数据
     */
    protected PageData<T> doPage(Function<Map<String,Object>,List<T>> function){
        return new PageData<>(getRequest(),null,function);
    }


    /**
     * 自定义方法分页
     *
     * @param customParam 自定义参数
     * @param function 方法
     * @return 分页数据
     */
    protected PageData<T> doPage(Map<String,Object> customParam, Function<Map<String,Object>,List<T>> function){
        return new PageData<>(getRequest(),customParam,function);
    }

    /**
     * 获得当前Request
     *
     * @return request
     */
    protected HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获得当前泛型类型
     *
     * @return 泛型类型
     */
    protected final Class<T> getBeanClazz() {
        return (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
