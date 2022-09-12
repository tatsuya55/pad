package com.pad.mapper;

import com.pad.entity.Bank;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 合作银行表bank Mapper 接口
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
public interface BankMapper extends BaseMapper<Bank> {
    //根据银行id删除银行
    void updateNo(List<String> id);
}
