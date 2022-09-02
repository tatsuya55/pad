package com.pad.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 地址表
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Address对象", description="地址表")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "address_areaId", type = IdType.ID_WORKER_STR)
    private String addressAreaId;

    @ApiModelProperty(value = "名称")
    private String addressName;

    @ApiModelProperty(value = "父id")
    @TableField("address_regionId")
    private String addressRegionId;


}
