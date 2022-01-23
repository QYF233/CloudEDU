package com.ndky.cloudedu.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.ndky.cloudedu.common.lang.ReturnMsg;
import com.ndky.cloudedu.common.utils.Util;
import com.ndky.cloudedu.entity.User;
import com.ndky.cloudedu.service.UserRoleService;
import com.ndky.cloudedu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * <p>
 * 登录、注册
 * </p>
 *
 * @author kiko
 * @since 2021-10-24
 */
@Api(tags = "登录管理")
@RestController
@RequestMapping("")
public class LoginController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;
    @Resource
    private DefaultKaptcha captchaProducer;
    /**
     * 登录验证码SessionKey
     */
    public static final String LOGIN_VALIDATE_CODE = "login_validate_code";

    /**
     * 登录验证码图片
     */
    @ApiOperation("获取登录验证码图片")
    @GetMapping(value = {"/getValidateCode"})
    public void loginValidateCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Util.validateCode(request, response, captchaProducer, LOGIN_VALIDATE_CODE);
    }

    @ApiOperation("后台登录")
    @PostMapping("/admin/login")
    @ResponseBody
    public ReturnMsg adminLogin(@RequestBody User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername());
        wrapper.eq("password", SecureUtil.md5(user.getPassword()));
        System.out.println(SecureUtil.md5(user.getPassword()));
        User one = userService.getOne(wrapper);
        if (one != null) {
            one.setLoginTime(LocalDateTime.now());
            userService.updateById(one);
            StpUtil.login(one.getId());
            String token = StpUtil.getTokenInfo().getTokenValue();
            return ReturnMsg.success("登录成功！").add("token", token);
        } else {
            return ReturnMsg.failed("用户名或密码错误");
        }
    }

    @ApiOperation("前台登录")
    @PostMapping("/login")
    @ResponseBody
    public ReturnMsg login(HttpServletRequest request, @RequestParam(value = "id", required = true) Integer id, @RequestParam(value = "password", required = true) String password, @RequestParam(value = "code", required = true) String code) {
        String loginValidateCode = request.getSession().getAttribute(LOGIN_VALIDATE_CODE).toString();
        if (loginValidateCode == null) {
            return ReturnMsg.failed("验证码过期！");
        } else if (loginValidateCode.equals(code)) {
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("id", id);
            wrapper.eq("password", SecureUtil.md5(password));
            User one = userService.getOne(wrapper);
            if (one != null) {
                one.setLoginTime(LocalDateTime.now());
                userService.updateById(one);
                StpUtil.login(one.getId());
                String token = StpUtil.getTokenInfo().getTokenValue();
                return ReturnMsg.success("登录成功！").add("token", token);
            } else {
                return ReturnMsg.failed("用户名或密码错误！");
            }
        } else {
            return ReturnMsg.failed("验证码错误！");
        }
    }

    @ApiOperation(value = "学生端注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg register(@Validated @RequestBody User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", user.getId());
        if (userService.getOne(wrapper) != null) {
            return ReturnMsg.failed("该学生已注册！请登录！");
        }
        user.setPassword(SecureUtil.md5(user.getPassword()));
        User newUser = userService.register(user);
        if (newUser == null) {
            return ReturnMsg.failed();
        } else {
            userRoleService.addRole(user.getId(), Convert.toLong(1));
            return ReturnMsg.success("注册成功！");
        }
    }
}
