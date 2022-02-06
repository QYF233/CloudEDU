package com.ndky.cloudedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ndky.cloudedu.entity.Colleges;
import com.ndky.cloudedu.vo.CollegesVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kiko
 * @since 2022-02-05
 */
@Mapper
public interface CollegesMapper extends BaseMapper<Colleges> {
    public List<CollegesVo> getAllColleges();
}
