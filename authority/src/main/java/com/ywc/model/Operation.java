package com.ywc.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "T_OPERATION")
public class Operation {
    /**
     * 操作id
     */
    @Id
    @Column(name = "OPERATION_ID")
    private Integer operationId;

    /**
     * 操作名称
     */
    @Column(name = "OPERATION_NAME")
    private String operationName;

    /**
     * 获取操作id
     *
     * @return OPERATION_ID - 操作id
     */
    public Integer getOperationId() {
        return operationId;
    }

    /**
     * 设置操作id
     *
     * @param operationId 操作id
     */
    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    /**
     * 获取操作名称
     *
     * @return OPERATION_NAME - 操作名称
     */
    public String getOperationName() {
        return operationName;
    }

    /**
     * 设置操作名称
     *
     * @param operationName 操作名称
     */
    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
}