package com.pad.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pad.entity.CompanyInfo;
import com.pad.entity.Credit;
import com.pad.mapper.CreditMapper;
import com.pad.service.CreditService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * <p>
 * 放款表 服务实现类
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Service
public class CreditServiceImpl extends ServiceImpl<CreditMapper, Credit> implements CreditService {
    @Override
    public IPage<Credit> pageQuery(IPage<Credit> page, Credit credit) {
        return baseMapper.pageQuery(page,credit);
    }
}
