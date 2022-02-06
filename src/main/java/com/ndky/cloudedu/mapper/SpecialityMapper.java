package com.ndky.cloudedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ndky.cloudedu.entity.Speciality;
import com.ndky.cloudedu.vo.SpecialityVo;
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
public interface SpecialityMapper extends BaseMapper<Speciality> {
    public List<SpecialityVo> selectAllSpecialities(Long colId);
}
