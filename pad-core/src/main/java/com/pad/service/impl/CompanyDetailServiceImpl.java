package com.pad.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.pad.entity.CompanyDetail;
import com.pad.mapper.CompanyDetailMapper;
import com.pad.service.CompanyDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.lettuce.core.dynamic.annotation.Param;
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
    public List<CompanyDetail> selectByFK(String cNo) {
        return mapper.selectByFK(cNo);
    }


    //逻辑删除
    @Override
    public void updateCompanyDetailByIds(List<String> id) {
        mapper.updateCompanyDetailByIds(id);
    }
}
