package com.pad.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pad.entity.CompanyInfo;
import com.pad.mapper.CompanyInfoMapper;
import com.pad.service.CompanyInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * <p>
 * 企业用户基本信息表company_info 服务实现类
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Service
public class CompanyInfoServiceImpl extends ServiceImpl<CompanyInfoMapper, CompanyInfo> implements CompanyInfoService {

    @Override
    public void pageQuery(Page<CompanyInfo> page, CompanyInfo companyInfo) {
        //构造条件
        LambdaQueryWrapper<CompanyInfo> wrapper = new LambdaQueryWrapper<CompanyInfo>();
        //判断条件是否为空
        if (ObjectUtils.isEmpty(companyInfo)){
            //条件为空 直接分页查询
            baseMapper.selectPage(page,null);
            return;
        }
        //判断单个条件是否为空
        //名称
        String name = companyInfo.getName();
        //手机
        String phone = companyInfo.getPhone();
        //邮箱
        String email = companyInfo.getEmail();
        //创建时间
        Date createTime = companyInfo.getCreateTime();
        //状态
        Integer isDeleted = companyInfo.getIsDeleted();
        //认证状态
        Integer authStatus = companyInfo.getAuthStatus();

        if (StringUtils.hasText(name)){
            wrapper.like(CompanyInfo::getName,name);
        }
        if (StringUtils.hasText(phone)){
            wrapper.like(CompanyInfo::getPhone,phone);
        }
        if (StringUtils.hasText(email)){
            wrapper.like(CompanyInfo::getEmail,phone);
        }
        if (createTime !=null){
            wrapper.eq(CompanyInfo::getCreateTime,createTime);
        }
        if (isDeleted !=null){
            wrapper.eq(CompanyInfo::getIsDeleted,isDeleted);
        }
        if (authStatus !=null){
            wrapper.eq(CompanyInfo::getAuthStatus,phone);
        }
        //查询
        baseMapper.selectPage(page,wrapper);
    }
}
