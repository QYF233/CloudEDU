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
 * @since 2021-10-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Room对象", description="")
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "教室编号")
    private Long id;

    @ApiModelProperty(value = "教室名称")
    private String name;

    @ApiModelProperty(value = "教室备注")
    private String note;

    @ApiModelProperty(value = "教室是否开放：1-开放，0-不开放")
    private Integer state;
}
