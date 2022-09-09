package com.pad.service;


import com.pad.entity.CompanyDetail;
import com.baomidou.mybatisplus.extension.service.IService;


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
    List<CompanyDetail> selectByFK(String cNo);

    /**
     * 逻辑删除企业用户详细信息
     * @param id
     */
    void updateCompanyDetailByIds(List<String> id);
}
