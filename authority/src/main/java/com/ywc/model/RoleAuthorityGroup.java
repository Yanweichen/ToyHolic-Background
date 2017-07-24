package com.ywc.model;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "T_ROLE_AUTHORITY_GROUP")
public class RoleAuthorityGroup {
    /**
     * 角色ID
     */
    @Column(name = "ROLE_ID")
    private Integer roleId;

    /**
     * 权限组ID
     */
    @Column(name = "AUTHORITY_GROUP_ID")
    private Integer authorityGroupId;

    /**
     * 获取角色ID
     *
     * @return ROLE_ID - 角色ID
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID
     *
     * @param roleId 角色ID
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

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
}