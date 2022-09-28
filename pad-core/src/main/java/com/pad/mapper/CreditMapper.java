package com.pad.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pad.entity.Credit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pad.entity.LoanInfo;

/**
 * <p>
 * 放款表 Mapper 接口
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
public interface CreditMapper extends BaseMapper<Credit> {

    //放款表分页显示
    IPage<Credit> pageQuery(IPage<Credit> page, Credit credit);
}
