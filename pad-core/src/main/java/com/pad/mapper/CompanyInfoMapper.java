package com.pad.mapper;

import com.pad.entity.CompanyInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 企业用户基本信息表company_info Mapper 接口
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
public interface CompanyInfoMapper extends BaseMapper<CompanyInfo> {

    //逻辑删除企业用户基本信息
    void deleteCompanyInfoByIds(List<String> cNo);

    //修改企业用户基本信息的认证状态值
    void updateCompanyInfoStatus(@Param("authStatus") Integer authStatus,@Param("cNo") String cNo);
}
