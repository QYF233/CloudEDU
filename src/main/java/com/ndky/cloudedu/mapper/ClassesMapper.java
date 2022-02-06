package com.ndky.cloudedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ndky.cloudedu.entity.Classes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author kiko
 * @since 2022-02-05
 */
@Mapper
public interface ClassesMapper extends BaseMapper<Classes> {
 public List<Classes> selectAllClasses(Long speId);
}
