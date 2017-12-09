package com.ywc.model;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "T_AUTHORITY_GROUP")
public class AuthorityGroup {
    /**
     * 权限组ID
     */
    @Column(name = "AUTHORITY_GROUP_ID")
    private Integer authorityGroupId;

    /**
     * 权限组名称
     */
    @Column(name = "AUTHORITY_GROUP_NAME")
    private String authorityGroupName;

    /**
     * 获取权限组ID
     *
     * @return AUTHORITY_GROUP_ID - 权限组ID
     */
    public Integer getAuthorityGroupId() {
        return authorityGroupId;
    }

    /**
     * 设置权限组ID
     *
     * @param authorityGroupId 权限组ID
     */
    public void setAuthorityGroupId(Integer authorityGroupId) {
        this.authorityGroupId = authorityGroupId;
    }

    /**
     * 获取权限组名称
     *
     * @return AUTHORITY_GROUP_NAME - 权限组名称
     */
    public String getAuthorityGroupName() {
        return authorityGroupName;
    }

    /**
     * 设置权限组名称
     *
     * @param authorityGroupName 权限组名称
     */
    public void setAuthorityGroupName(String authorityGroupName) {
        this.authorityGroupName = authorityGroupName;
    }
}