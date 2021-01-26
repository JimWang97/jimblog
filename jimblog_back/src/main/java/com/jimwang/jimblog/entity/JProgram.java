package com.jimwang.jimblog.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jimwang
 * @since 2021-01-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="JProgram对象", description="")
@TableName(value = "j_program")
public class JProgram implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "项目id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "项目名称")
    private String title;

    @ApiModelProperty(value = "项目简介")
    private String content;

    @ApiModelProperty(value = "项目地址")
    private String url;

    @TableLogic
    @ApiModelProperty(value = "逻辑删除 0未删除 1删除")
    private Boolean deleted;

    @ApiModelProperty(value = "最新操作时间")
    private Date lastEdit;

    @ApiModelProperty(value = "项目创建时间")
    @TableField(fill= FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "项目更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
