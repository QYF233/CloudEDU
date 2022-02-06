package com.ndky.cloudedu.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ndky.cloudedu.entity.Colleges;
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
@ApiModel(value = "Colleges对象", description = "")
public class CollegesVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学院编号")
    @TableId(type = IdType.AUTO)
    private Long value;

    @ApiModelProperty(value = "学院名称")
    private String label;

    @ApiModelProperty(value = "包含专业")
    private List<SpecialityVo> children;

}
