package com.ywc.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "T_AUTHORITY")
public class Authority {
    /**
     * 权限id
     */
    @Id
    @Column(name = "AUTHORITY_ID")
    private Integer authorityId;

    /**
     * 资源id
     */
    @Column(name = "RESOURCE_ID")
    private Integer resourceId;

    /**
     * 操作id
     */
    @Column(name = "OPERATION_ID")
    private Integer operationId;

    /**
     * 权限副ID
     */
    @Column(name = "AUTHORITY_PARENT_ID")
    private Integer authorityParentId;

    /**
     * 权限状态
     */
    @Column(name = "AUTHORITY_STATUS")
    private Integer authorityStatus;

    /**
     * 获取权限id
     *
     * @return AUTHORITY_ID - 权限id
     */
    public Integer getAuthorityId() {
        return authorityId;
    }

    /**
     * 设置权限id
     *
     * @param authorityId 权限id
     */
    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    /**
     * 获取资源id
     *
     * @return RESOURCE_ID - 资源id
     */
    public Integer getResourceId() {
        return resourceId;
    }

    /**
     * 设置资源id
     *
     * @param resourceId 资源id
     */
    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

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
     * 获取权限副ID
     *
     * @return AUTHORITY_PARENT_ID - 权限副ID
     */
    public Integer getAuthorityParentId() {
        return authorityParentId;
    }

    /**
     * 设置权限副ID
     *
     * @param authorityParentId 权限副ID
     */
    public void setAuthorityParentId(Integer authorityParentId) {
        this.authorityParentId = authorityParentId;
    }

    /**
     * 获取权限状态
     *
     * @return AUTHORITY_STATUS - 权限状态
     */
    public Integer getAuthorityStatus() {
        return authorityStatus;
    }

    /**
     * 设置权限状态
     *
     * @param authorityStatus 权限状态
     */
    public void setAuthorityStatus(Integer authorityStatus) {
        this.authorityStatus = authorityStatus;
    }
}