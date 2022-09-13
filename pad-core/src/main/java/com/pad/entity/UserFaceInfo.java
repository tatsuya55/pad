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
 * 人脸识别recognition
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Recognition对象", description="人脸识别recognition")
public class UserFaceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "企业编号")
    private String cNo;

    @ApiModelProperty(value = "分组id")
    private String groupId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "人脸唯一ID")
    private String faceId;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "邮箱")
    private Integer email;

    @ApiModelProperty(value = "性别")
    private Integer gender;

    @ApiModelProperty(value = "电话")
    private String phoneNumber;



    @ApiModelProperty(value = "人脸特征")
    private byte[] faceFeature;


    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT)
    private Date updateTime;



    @ApiModelProperty(value = "状态  0未审核，1审核通过，-1审核失败   默认是0未审核")
    private Integer status;


}
