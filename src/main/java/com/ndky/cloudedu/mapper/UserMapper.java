package com.ndky.cloudedu.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndky.cloudedu.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ndky.cloudedu.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kiko
 * @since 2021-10-20
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    Page<UserVo> getUserAll(IPage<UserVo> page, @Param("keyword") String keyword);
}
