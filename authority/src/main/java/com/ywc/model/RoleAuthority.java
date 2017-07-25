package com.ywc.model;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "T_ROLE_AUTHORITY")
public class RoleAuthority {
    /**
     * 角色ID
     */
    @Column(name = "ROLE_ID")
    private Integer roleId;

    /**
     * 权限ID
     */
    @Column(name = "AUTHORITY_ID")
    private Integer authorityId;

    /**
     * 权限状态ID 1 增加 0 删除
     */
    @Column(name = "ROLE_AUTHORITY_STATUS")
    private Integer roleAuthorityStatus;

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
     * 获取权限ID
     *
     * @return AUTHORITY_ID - 权限ID
     */
    public Integer getAuthorityId() {
        return authorityId;
    }

    /**
     * 设置权限ID
     *
     * @param authorityId 权限ID
     */
    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    /**
     * 获取权限状态
     */
    public Integer getRoleAuthorityStatus() {
        return roleAuthorityStatus;
    }

    /**
     * 设置权限状态
     */
    public RoleAuthority setRoleAuthorityStatus(Integer roleAuthorityStatus) {
        this.roleAuthorityStatus = roleAuthorityStatus;
        return this;
    }
}