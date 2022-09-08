package com.pad.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pad.entity.Admin;
import com.pad.entity.Bank;
import com.pad.mapper.BankMapper;
import com.pad.service.BankService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 合作银行表bank 服务实现类
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Service
public class BankServiceImpl extends ServiceImpl<BankMapper, Bank> implements BankService {

    @Resource
    private BankMapper bankMapper;

    @Override
    public void pageQuery(Page<Bank> bankPage, Bank bank) {
        //构造条件
        LambdaQueryWrapper<Bank> wrapper = new LambdaQueryWrapper<>();
        //判断条件是否为空
        if (ObjectUtils.isEmpty(bank)){
            //条件为空 直接分页查询
            baseMapper.selectPage(bankPage,null);
            return;
        }
        //判断单个条件是否为空
        //银行名
        String bankName = bank.getBankName();
        //状态
        Integer isDeleted = bank.getIsDeleted();
        if (StringUtils.hasText(bankName)){
            wrapper.like(Bank::getBankName,bankName);
        }
        if (isDeleted !=null){
            wrapper.eq(Bank::getIsDeleted,isDeleted);
        }
        //查询
        baseMapper.selectPage(bankPage,wrapper);
    }

    @Override
    public String selectBankByBankId(List<String> id) {
        return bankMapper.selectBankByBankId(id);
    }
}
