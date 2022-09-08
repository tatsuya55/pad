package com.pad.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pad.entity.Bank;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 合作银行表bank 服务类
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
public interface BankService extends IService<Bank> {
    //银行列表分页显示
    void pageQuery(Page<Bank> bankPage, Bank bank);
    //根据用户id查询对应银行
    String selectBankByBankId(List<String> id);
}
