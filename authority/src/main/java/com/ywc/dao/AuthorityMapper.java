package com.ywc.dao;

import com.ywc.model.Authority;
import com.ywc.model.dto.AuthorityDTO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AuthorityMapper extends Mapper<Authority> {

    /**
     * 根据用户ID 查询权限
     *
     * @param userId 用户ID
     * @return 权限
     */
    List<AuthorityDTO> selectUserAuthority(Integer userId);

    /**
     * 根据角色ID查询权限
     *
     * @param roleId 角色ID
     * @return 角色ID
     */
    List<AuthorityDTO> selectRoleAuthority(Integer roleId);
}