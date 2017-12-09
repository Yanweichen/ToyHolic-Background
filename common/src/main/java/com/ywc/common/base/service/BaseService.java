package com.ywc.common.base.service;

import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Service基类
 *
 * @author yanwe
 *         createTime 2017-07-2017/7/26 11:31
 */
public interface BaseService<T> {

    List<T> selectByExample(Example example);
}
