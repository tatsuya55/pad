package com.pad.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 合作银行表bank
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Bank对象", description="合作银行表bank")
public class Bank implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "银行编号")
    @TableId(value = "bank_no", type = IdType.ID_WORKER_STR)
    private String bankNo;

    @ApiModelProperty(value = "银行名称")
    private String bankName;

    @ApiModelProperty(value = "年化利率")
    private Double borrowYearRate;

    @ApiModelProperty(value = "逾期利率")
    private Double overdueRate;

    @ApiModelProperty(value = "产品说明")
    private String description;

    @ApiModelProperty(value = "银行电话")
    private String phone;

    @ApiModelProperty(value = "注册省    外键")
    private Integer province;

    @ApiModelProperty(value = "注册市    外键")
    private Integer city;

    @ApiModelProperty(value = "注册区    外键")
    private Integer area;

    @ApiModelProperty(value = "银行详细地址")
    private String address;

    @ApiModelProperty(value = "逻辑删除   0已删除，1未删除   默认是1未删除")
    private Integer isDeleted;


}
