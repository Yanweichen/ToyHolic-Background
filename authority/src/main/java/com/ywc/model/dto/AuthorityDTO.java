package com.ywc.model.dto;

/**
 * 权限DTO
 *
 * @author yanwe
 *         createTime 2017-07-2017/7/25 16:21
 */
public class AuthorityDTO {

    private Integer authorityId;

    private Integer authorityParentId;

    private String resourceName;

    private String operationName;

    public Integer getAuthorityId() {
        return authorityId;
    }

    public AuthorityDTO setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
        return this;
    }

    public Integer getAuthorityParentId() {
        return authorityParentId;
    }

    public AuthorityDTO setAuthorityParentId(Integer authorityParentId) {
        this.authorityParentId = authorityParentId;
        return this;
    }

    public String getResourceName() {
        return resourceName;
    }

    public AuthorityDTO setResourceName(String resourceName) {
        this.resourceName = resourceName;
        return this;
    }

    public String getOperationName() {
        return operationName;
    }

    public AuthorityDTO setOperationName(String operationName) {
        this.operationName = operationName;
        return this;
    }
}
