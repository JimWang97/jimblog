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
 * @since 2021-01-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="JBlog对象", description="")
@TableName(value = "j_blog")
public class JBlog implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "博客id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "博客名称")
    private String title;

    @ApiModelProperty(value = "博客内容 md格式")
    private String content;

    @ApiModelProperty(value = "最近更新时间")
    private Date lastEdit;

    @ApiModelProperty(value = "博客创建时间")
    @TableField(fill= FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "数据更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @ApiModelProperty(value = "是否发布 默认0为未发布")
    private Boolean published;

    @ApiModelProperty(value = "是否推荐 默认1为推荐")
    private Boolean recommend;

    @TableLogic
    @ApiModelProperty(value = "逻辑删除 0未删除 1删除 默认0")
    private Boolean deleted;

    @ApiModelProperty(value = "博客浏览量")
    private Integer views;

    @ApiModelProperty(value = "所属类型 原创0 转载1")
    private Boolean type;


}
