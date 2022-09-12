package com.pad.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pad.entity.CompanyInfo;
import com.pad.entity.LoanInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pad.vo.LoanInfoVo;

import java.util.List;

/**
 * <p>
 * 贷款信息表 Mapper 接口
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
public interface LoanInfoMapper extends BaseMapper<LoanInfo> {

    //贷款信息表分页显示
    void pageQuery(Page<LoanInfo> Page, LoanInfo loanInfo);

    //逻辑删除贷款信息表
    void deleteLoanInfoByIds(List<String> ids);

    //按贷款编号查询每个贷款信息
    void findById(String id);
}
