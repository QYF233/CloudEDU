package com.ndky.cloudedu.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
@ApiModel(value="Classes对象", description="")
public class ClassesVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "班级号")
    @TableId(type = IdType.AUTO)
    private Long value;

    @ApiModelProperty(value = "班级名称")
    private String label;

}
