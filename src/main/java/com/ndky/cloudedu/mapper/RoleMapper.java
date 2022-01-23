package com.ndky.cloudedu.mapper;

import com.ndky.cloudedu.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 后台用户角色表 Mapper 接口
 * </p>
 *
 * @author kiko
 * @since 2021-10-20
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}
