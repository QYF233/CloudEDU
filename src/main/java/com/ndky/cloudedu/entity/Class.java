package com.ndky.cloudedu.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author kiko
 * @since 2021-10-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Class对象", description="")
public class Class implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "班级号")
    private Integer id;

    @ApiModelProperty(value = "班级名称")
    private String className;

    @ApiModelProperty(value = "人数")
    private Integer number;

    @ApiModelProperty(value = "学院")
    private String dept;

}
