package com.pad.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.pad.entity.CompanyDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 * 企业用户详细信息表 服务类
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
public interface CompanyDetailService extends IService<CompanyDetail> {


    /**
     * 外键查询
     */
    List<CompanyDetail> selectByPK(String cNo);
}
