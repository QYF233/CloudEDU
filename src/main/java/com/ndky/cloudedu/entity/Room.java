package com.ndky.cloudedu.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Room对象", description = "")
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "教室编号")
//    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "教室名称")
    private String name;

    @ApiModelProperty(value = "教室备注")
    private String note;

    @ApiModelProperty(value = "教室是否开放：1-开放，0-不开放")
    private Integer state;

    @ApiModelProperty(value = "直播地址")
    private String liveUrl;

    @ApiModelProperty(value = "聊天室地址")
    private String socketUrl;

    @ApiModelProperty(value = "教师id")
    private Long teacherId;

    public Room(String name, String note, Integer state, String liveUrl, Long teacherId) {
        this.name = name;
        this.note = note;
        this.state = state;
        this.liveUrl = liveUrl;
        this.teacherId = teacherId;
    }

    public Room(Long id, String name, String note, Integer state, String liveUrl, Long teacherId) {
        this.id = id;
        this.name = name;
        this.note = note;
        this.state = state;
        this.liveUrl = liveUrl;
        this.teacherId = teacherId;
    }
}
