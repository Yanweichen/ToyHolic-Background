package com.ywc.model;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "T_AUTHORITY_GROUP_RELEVANCE")
public class AuthorityGroupRelevance {
    /**
     * 权限组ID
     */
    @Column(name = "AUTHORITY_GROUP_ID")
    private Integer authorityGroupId;

    /**
     * 权限ID
     */
    @Column(name = "AUTHORITY_ID")
    private Integer authorityId;

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