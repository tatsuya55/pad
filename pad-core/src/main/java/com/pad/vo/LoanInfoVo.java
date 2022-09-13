package com.pad.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="LoanInfoVo对象", description="贷款信息表")
public class LoanInfoVo implements Serializable {

    @ApiModelProperty(value = "贷款信息编号")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "企业编号")
    private String cNo;

    @ApiModelProperty(value = "银行编号")
    private String bankNo;

    @ApiModelProperty(value = "收款账户")
    private String bankNumber;

    @ApiModelProperty(value = "开户行")
    private String bankType;

    @ApiModelProperty(value = "贷款金额")
    private Double amount;

    @ApiModelProperty(value = "贷款用途")
    private String purpose;

    @ApiModelProperty(value = "借款期限")
    private Date period;

    @ApiModelProperty(value = "还款方式 1等额本息，2等额本金，3每月还息，4一次性还")
    private Integer returnMethod;

    @ApiModelProperty(value = "状态 0未审核，1审核通过，-1审核失败")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除 0已删除，1未删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "企业名称")
    private String name;

    @ApiModelProperty(value = "银行名称")
    private String bankName;
}
