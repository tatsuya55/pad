package com.pad.service.impl;

import com.pad.entity.Notice;
import com.pad.mapper.NoticeMapper;
import com.pad.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公告表notice 服务实现类
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

}
