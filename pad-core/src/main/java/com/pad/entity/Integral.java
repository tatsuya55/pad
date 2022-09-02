package com.pad.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 积分表
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Integral对象", description="积分表")
public class Integral implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "积分编号")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "企业编号")
    private String cNo;

    @ApiModelProperty(value = "积分数值")
    private Integer value;

    @ApiModelProperty(value = "获得时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


}
