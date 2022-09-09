package com.pad.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pad.entity.Admin;
import com.pad.entity.CompanyInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 企业用户基本信息表company_info 服务类
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
public interface CompanyInfoService extends IService<CompanyInfo> {

    //企业用户基本信息表分页显示
    void pageQuery(Page<CompanyInfo> Page, CompanyInfo companyInfo);

    //逻辑删除企业用户基本信息
    void deleteCompanyInfoByIds(List<String> cNo);

    //修改企业用户基本信息的认证状态值
    void updateCompanyInfoStatus(Integer authStatus,String cNo);
}
