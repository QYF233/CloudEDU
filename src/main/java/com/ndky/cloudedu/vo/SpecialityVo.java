package com.ndky.cloudedu.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ndky.cloudedu.entity.Classes;
import com.ndky.cloudedu.entity.Speciality;
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
public class SpecialityVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "专业id")
    @TableId(value = "speId", type = IdType.AUTO)
    private Long value;

    @ApiModelProperty(value = "专业名称	")
    private String label;

    @ApiModelProperty(value = "包含班级")
    private List<Classes> children;
}
