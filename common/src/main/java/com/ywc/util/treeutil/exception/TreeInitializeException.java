package com.ywc.util.treeutil.exception;

/**
 * TreeUtil初始化异常
 * 数据源为 Null 或 为空 或 parentId/topId 为 Null 会抛出
 *
 * @author yanwe
 *         createTime 2017-01-2017/1/23 9:28
 */
public class TreeInitializeException extends Exception{

    public TreeInitializeException(String message) {
        super(message);
    }
}
