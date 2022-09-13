package com.pad.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 管理员信息表
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Admin对象", description="管理员信息表")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Excel(name = "用户编号")
    @ApiModelProperty(value = "管理员编号")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @Excel(name = "用户姓名")
    @ApiModelProperty(value = "姓名")
    private String name;

    @Excel(name = "用户手机号")
    @ApiModelProperty(value = "手机号")
    private String phone;

    @ExcelIgnore
    @ApiModelProperty(value = "密码")
    private String password;

    @Excel(name = "用户手机号")
    @ApiModelProperty(value = "邮箱")
    private String email;

    @Excel(name = "用户状态",replace = {"已停用_0","未停用_1"})
    @ApiModelProperty(value = "0已停用，1未停用")
    private Integer status;

    @ExcelIgnore
    @ApiModelProperty(value = "0已删除，1未删除")
    private Integer isDeleted;

    //新加属性 不映射到数据库 不添加到redis 忽略该字段
    @ExcelIgnore
    @JSONField(serialize = false)
    @ApiModelProperty(value = "用户对应角色ids")
    @TableField(exist = false)
    private List<Integer> roleIds;

}
