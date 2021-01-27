package com.jimwang.jimblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jimwang
 * @since 2021-01-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="JBlogTag对象", description="")
@AllArgsConstructor
@NoArgsConstructor
public class JBlogTag implements Serializable {

    private static final long serialVersionUID=1L;

    private Long blogId;

    @ApiModelProperty(value = "标签名")
    private String tag;

    @TableField(exist = false)
    private Long tagNums;
}
