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
 * 企业用户材料信息表
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CompanyMaterial对象", description="企业用户材料信息表")
public class CompanyMaterial implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "材料编号")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "企业编号")
    private String cNo;

    @ApiModelProperty(value = "法人电话")
    private String legalPhone;

    @ApiModelProperty(value = "法人姓名")
    private String legalName;

    @ApiModelProperty(value = "法人证件号")
    private String legalId;

    @ApiModelProperty(value = "法人证件图片")
    private String legalImg;

    @ApiModelProperty(value = "六个月流水，财务报表图片")
    private String turnover;

    @ApiModelProperty(value = "企业市值")
    private Double value;

    @ApiModelProperty(value = "企业征信")
    private String credit;

    @ApiModelProperty(value = "抵押物")
    private String collateral;

    @ApiModelProperty(value = "纳税记录")
    private String records;

    @ApiModelProperty(value = "状态 0未审核，1审核通过，-1审核失败")
    private Integer status;


}
