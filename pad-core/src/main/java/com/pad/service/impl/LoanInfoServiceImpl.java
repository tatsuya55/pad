package com.pad.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pad.entity.LoanInfo;
import com.pad.mapper.LoanInfoMapper;
import com.pad.service.LoanInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 贷款信息表 服务实现类
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Service
public class LoanInfoServiceImpl extends ServiceImpl<LoanInfoMapper, LoanInfo> implements LoanInfoService {
    @Override
    public void pageQuery(Page<LoanInfo> page, LoanInfo loanInfo) {
        baseMapper.pageQuery(page, loanInfo);
    }

    @Override
    public void deleteLoanInfoByIds(List<String> ids) {
        baseMapper.deleteLoanInfoByIds(ids);
    }

    @Override
    public LoanInfo findById(String id) {
        return baseMapper.findById(id);
    }
}
