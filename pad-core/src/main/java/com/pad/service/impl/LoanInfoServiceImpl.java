package com.pad.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pad.entity.LoanInfo;
import com.pad.mapper.LoanInfoMapper;
import com.pad.service.LoanInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
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
    public void pageQuery(Page<LoanInfo> Page, LoanInfo LoanInfo) {
        //构造条件
        LambdaQueryWrapper<LoanInfo> wrapper = new LambdaQueryWrapper<LoanInfo>();
        //判断条件是否为空
        if (ObjectUtils.isEmpty(LoanInfo)){
            //条件为空 直接分页查询
            baseMapper.selectPage(Page,null);
            return;
        }
        //判断单个条件是否为空
        //企业编号
        String name = LoanInfo.getName();
        //银行编号
        String bankName = LoanInfo.getBankName();
        //贷款金额
        Double amount = LoanInfo.getAmount();
        //贷款用途
        String purpose = LoanInfo.getPurpose();
        //借款期限
        Date period = LoanInfo.getPeriod();
        //状态
        Integer status = LoanInfo.getStatus();

        if (StringUtils.hasText(name)){
            wrapper.like(LoanInfo::getName,name);
        }
        if (StringUtils.hasText(bankName)){
            wrapper.like(LoanInfo::getBankName,bankName);
        }
        if (amount != null){
            wrapper.like(LoanInfo::getAmount,amount);
        }
        if (StringUtils.hasText(purpose)){
            wrapper.like(LoanInfo::getPurpose,purpose);
        }
        if (period !=null){
            wrapper.like(LoanInfo::getPeriod,period);
        }
        if (status !=null){
            wrapper.like(LoanInfo::getStatus,status);
        }
        //查询
        baseMapper.selectPage(Page,wrapper);
    }

    @Override
    public void deleteLoanInfoByIds(List<String> ids) {
        baseMapper.deleteLoanInfoByIds(ids);
    }
}
