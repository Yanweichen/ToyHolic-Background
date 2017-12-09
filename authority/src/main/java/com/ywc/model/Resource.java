package com.ywc.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "T_RESOURCE")
public class Resource {
    /**
     * 资源ID
     */
    @Id
    @Column(name = "RESOURCE_ID")
    private Integer resourceId;

    /**
     * 资源名称
     */
    @Column(name = "RESOURCE_NAME")
    private String resourceName;

    /**
     * 资源描述
     */
    @Column(name = "RESOURCE_DESCRIPTION")
    private String resourceDescription;

    /**
     * 获取资源ID
     *
     * @return RESOURCE_ID - 资源ID
     */
    public Integer getResourceId() {
        return resourceId;
    }

    /**
     * 设置资源ID
     *
     * @param resourceId 资源ID
     */
    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * 获取资源名称
     *
     * @return RESOURCE_NAME - 资源名称
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * 设置资源名称
     *
     * @param resourceName 资源名称
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * 获取资源描述
     *
     * @return RESOURCE_DESCRIPTION - 资源描述
     */
    public String getResourceDescription() {
        return resourceDescription;
    }

    /**
     * 设置资源描述
     *
     * @param resourceDescription 资源描述
     */
    public void setResourceDescription(String resourceDescription) {
        this.resourceDescription = resourceDescription;
    }
}