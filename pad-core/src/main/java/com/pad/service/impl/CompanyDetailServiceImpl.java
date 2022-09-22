package com.pad.service.impl;


import com.pad.entity.CompanyDetail;
import com.pad.mapper.CompanyDetailMapper;
import com.pad.service.CompanyDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 企业用户详细信息表 服务实现类
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Service
public class CompanyDetailServiceImpl extends ServiceImpl<CompanyDetailMapper, CompanyDetail> implements CompanyDetailService {

    @Autowired
    private CompanyDetailMapper mapper;



    //外键查询
    @Override
    public CompanyDetail selectByFK(String cNo) {
        return mapper.selectByFK(cNo);
    }


    //逻辑删除
    @Override
    public void updateCompanyDetailByIds(List<String> id) {
        mapper.updateCompanyDetailByIds(id);
    }

    @Override
    public void deleteCompanyDetailBycNo(String cNo) {
        mapper.deleteCompanyDetailBycNo(cNo);
    }
}
