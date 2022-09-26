package com.pad.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pad.entity.Overdue;
import com.pad.mapper.OverdueMapper;
import com.pad.service.OverdueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 逾期信息表 服务实现类
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Service
public class OverdueServiceImpl extends ServiceImpl<OverdueMapper, Overdue> implements OverdueService {
    @Override
    public void pageQuery(Page<Overdue> page, Overdue overdue) {
        baseMapper.selectPage(page,null);
    }
}
