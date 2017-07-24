package com.ywc.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "T_ROLE")
public class Role {
    @Id
    @Column(name = "ROLE_ID")
    private Integer roleId;

    /**
     * 角色名称
     */
    @Column(name = "ROLE_NAME")
    private String roleName;

    /**
     * 角色状态
     */
    @Column(name = "ROLE_STATUS")
    private Integer roleStatus;

    /**
     * @return ROLE_ID
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取角色名称
     *
     * @return ROLE_NAME - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取角色状态
     *
     * @return ROLE_STATUS - 角色状态
     */
    public Integer getRoleStatus() {
        return roleStatus;
    }

    /**
     * 设置角色状态
     *
     * @param roleStatus 角色状态
     */
    public void setRoleStatus(Integer roleStatus) {
        this.roleStatus = roleStatus;
    }
}