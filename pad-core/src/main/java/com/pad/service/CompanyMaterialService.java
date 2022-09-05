package com.pad.service;

import com.github.pagehelper.PageInfo;
import com.pad.entity.CompanyMaterial;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 企业用户材料信息表 服务类
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
public interface CompanyMaterialService extends IService<CompanyMaterial> {

    /**
     * 模糊查询
     */
    PageInfo<CompanyMaterial> findByComplex(Integer pageNum, Integer pageSize, CompanyMaterial companyMaterial);

    /**
     * 外键查询
     */
    List<CompanyMaterial> selectByPK(String cNo);

}
