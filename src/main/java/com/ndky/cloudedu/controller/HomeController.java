package com.ndky.cloudedu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndky.cloudedu.common.lang.ReturnMsg;
import com.ndky.cloudedu.entity.Class;
import com.ndky.cloudedu.entity.Picture;
import com.ndky.cloudedu.entity.User;
import com.ndky.cloudedu.service.ClassService;
import com.ndky.cloudedu.service.PictureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页
 * </p>
 *
 * @author kiko
 * @since 2021-10-20
 */
@Api(tags = "首页管理")
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    PictureService pictureService;

    @Autowired
    ClassService classService;


    /**
     * 获取轮播图数据
     *
     * @return 图片列表
     */
    @ApiOperation(value = "获取轮播图数据")
    @GetMapping(value = "/getSwiper")
    public ReturnMsg getSwiper() {
        List<Picture> pictures = pictureService.selectPicture(5);
        if (pictures != null) {
            return ReturnMsg.success(pictures);
        } else {
            return ReturnMsg.failed();
        }
    }

}
