package com.ndky.cloudedu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ndky.cloudedu.vo.SpecialityVo;
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
@ApiModel(value="Colleges对象", description="")
public class Colleges implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学院编号")
    @TableId(value = "colId", type = IdType.AUTO)
    private Long colId;

    @ApiModelProperty(value = "学院名称")
    private String colName;

}
