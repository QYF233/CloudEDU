package com.ndky.cloudedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ndky.cloudedu.entity.Colleges;
import com.ndky.cloudedu.mapper.CollegesMapper;
import com.ndky.cloudedu.vo.CollegesVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kiko
 * @since 2022-02-05
 */
public interface CollegesService extends IService<Colleges> {

    public List<CollegesVo> getAllClassesOption();
}
