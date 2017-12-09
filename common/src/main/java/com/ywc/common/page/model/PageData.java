package com.ywc.common.page.model;

import com.github.pagehelper.PageInfo;
import com.ywc.common.page.PageHandler;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 分页数据封装
 *
 * @author yanwe
 *         createTime 2017-07-2017/7/26 11:35
 */
public class PageData<T>{

    private Integer startPage;

    private Integer pageSize;

    private Long total;

    private List<T> data;

    public PageData(HttpServletRequest request,Class<T> clazz,Mapper<T> mapper) {
        convert2PageData(PageHandler.getInstance().processPage(request,clazz,mapper));
    }

    public PageData(HttpServletRequest request,Class<T> clazz,Example example, Mapper<T> mapper) {
        convert2PageData(PageHandler.getInstance().processPage(request,clazz,example,mapper));
    }

    public PageData(HttpServletRequest request,Map<String,Object> customParam, Function<Map<String,Object>
            ,List<T>> function) {
        convert2PageData(PageHandler.getInstance().processPage(request,customParam,function));
    }

    private void convert2PageData(PageInfo<T> pageInfo){
        this.data = pageInfo.getList();
        this.pageSize = pageInfo.getPageSize();
        this.startPage = pageInfo.getPageNum();
        this.total = pageInfo.getTotal();
    }

    public Integer getStartPage() {
        return startPage;
    }

    public PageData setStartPage(Integer startPage) {
        this.startPage = startPage;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public PageData setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Long getTotal() {
        return total;
    }

    public PageData setTotal(Long total) {
        this.total = total;
        return this;
    }

    public List<T> getData() {
        return data;
    }

    public PageData setData(List<T> data) {
        this.data = data;
        return this;
    }
}
