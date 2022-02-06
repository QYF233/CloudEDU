package com.ndky.cloudedu.controller;


import com.ndky.cloudedu.common.lang.ReturnMsg;
import com.ndky.cloudedu.common.utils.Util;
import com.ndky.cloudedu.service.CollegesService;
import com.ndky.cloudedu.vo.CollegesVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author kiko
 * @since 2022-02-05
 */
@Api(tags = "学院管理")
@RestController
@RequestMapping("/colleges")
public class CollegesController {
    @Autowired
    public CollegesService collegesService;

    @ApiOperation("获取所有班级")
    @GetMapping(value = {"/getAllClassesOption"})
    public ReturnMsg getAllClassesOption() throws Exception {
        List<CollegesVo> allClasses = collegesService.getAllClassesOption();
        return ReturnMsg.success(allClasses);
    }
}

