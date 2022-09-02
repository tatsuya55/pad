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
 * 企业用户基本信息表company_info
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CompanyInfo对象", description="企业用户基本信息表company_info")
public class CompanyInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "企业编号")
    @TableId(value = "c_no", type = IdType.INPUT)
    private String cNo;

    @ApiModelProperty(value = "企业名称")
    private String name;

    @ApiModelProperty(value = "企业邮箱")
    private String email;

    @ApiModelProperty(value = "企业电话")
    private String phone;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "认证状态   0未认证，1认证中，2认证通过，-1认证失败     默认是0未认证")
    private Integer authStatus;

    @ApiModelProperty(value = "逻辑删除   0已删除，1未删除     默认是1未删除")
    private Integer isDeleted;


}
