package com.pad.service.impl;

import com.pad.entity.Bank;
import com.pad.mapper.BankMapper;
import com.pad.service.BankService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
