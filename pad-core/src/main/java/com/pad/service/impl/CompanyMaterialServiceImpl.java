package com.pad.service.impl;

import com.pad.entity.CompanyMaterial;
import com.pad.mapper.CompanyMaterialMapper;
import com.pad.service.CompanyMaterialService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 企业用户材料信息表 服务实现类
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Service
public class CompanyMaterialServiceImpl extends ServiceImpl<CompanyMaterialMapper, CompanyMaterial> implements CompanyMaterialService {


    @Autowired
    private CompanyMaterialMapper mapper;


    //外键查询
    @Override
    public CompanyMaterial selectByFK(String cNo) {
        return mapper.selectByFK(cNo);
    }

    @Override
    public void deleteCompanyMaterialByIds(String cNo) {
        mapper.deleteCompanyMaterialByIds(cNo);
    }
}
