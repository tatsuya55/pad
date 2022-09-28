package com.pad.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pad.entity.CompanyInfo;
import com.pad.entity.Credit;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 放款表 服务类
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
public interface CreditService extends IService<Credit> {

    //放款表分页显示
    IPage<Credit> pageQuery(IPage<Credit> page, Credit credit);
}
