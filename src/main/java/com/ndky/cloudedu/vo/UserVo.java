package com.ndky.cloudedu.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ndky.cloudedu.entity.Class;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UserVo对象", description = "")
public class UserVo  implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学号")
    private Long id;

    @ApiModelProperty(value = "姓名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "性别：1-男，2-女")
    private Integer sex;

    @ApiModelProperty(value = "身份：0-超级管理员，1-学生，2-老师，3-巡逻员")
    private Integer identity;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "介绍")
    private String note;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime crateTime;

    @ApiModelProperty(value = "登录时间")
    private LocalDateTime loginTime;

    @ApiModelProperty(value = "是否启用：1-启用，2-禁用")
    private Integer status;

    @ApiModelProperty(value = "班级id")
    private Long cid;

    @ApiModelProperty(value = "班级名称")
    private String className;

    @ApiModelProperty(value = "人数")
    private Integer number;

    @ApiModelProperty(value = "学院")
    private String dept;
}
