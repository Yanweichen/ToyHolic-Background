package com.ywc.model;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "T_USER_AUTHORITY")
public class UserAuthority {
    /**
     * 用户id
     */
    @Column(name = "USER_ID")
    private Integer userId;

    /**
     * 权限id
     */
    @Column(name = "AUTHORITY_ID")
    private Integer authorityId;

    /**
     * 用户权限状态
     */
    @Column(name = "USER_AUTHORITY_STATUS")
    private Integer userAuthorityStatus;

    /**
     * 获取用户id
     *
     * @return USER_ID - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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
     * 获取用户权限状态
     *
     * @return USER_AUTHORITY_STATUS - 用户权限状态
     */
    public Integer getUserAuthorityStatus() {
        return userAuthorityStatus;
    }

    /**
     * 设置用户权限状态
     *
     * @param userAuthorityStatus 用户权限状态
     */
    public void setUserAuthorityStatus(Integer userAuthorityStatus) {
        this.userAuthorityStatus = userAuthorityStatus;
    }
}