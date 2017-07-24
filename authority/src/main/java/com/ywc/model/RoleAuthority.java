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
}