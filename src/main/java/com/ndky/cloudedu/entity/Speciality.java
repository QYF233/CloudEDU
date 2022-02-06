package com.ndky.cloudedu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author kiko
 * @since 2022-02-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Speciality对象", description = "")
public class Speciality implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "专业id")
    @TableId(value = "speId", type = IdType.AUTO)
    private Long speId;

    @ApiModelProperty(value = "专业名称	")
    private String speName;

    @ApiModelProperty(value = "所属学院")
    private Long speCollege;

    @ApiModelProperty(value = "学制")
    private Integer speYears;

}
