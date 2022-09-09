package com.pad.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pad.entity.Message;
import com.pad.mapper.MessageMapper;
import com.pad.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 留言表 服务实现类
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Resource
    private MessageMapper messageMapper;

    @Override
    public void pageQuery(Page<Message> messagePage, Message message) {
        //构造条件
        LambdaQueryWrapper<Message> wrapper = new LambdaQueryWrapper<>();
        //判断条件是否为空
        if (ObjectUtils.isEmpty(message)){
            //条件为空 直接分页查询
            baseMapper.selectPage(messagePage,null);
            return;
        }
        //判断单个条件是否为空
        //留言内容
        String context = message.getContext();
        //状态
        Integer isDeleted = message.getIsDeleted();
        if (StringUtils.hasText(context)){
            wrapper.like(Message::getContext,context);
        }
        if (isDeleted !=null){
            wrapper.eq(Message::getIsDeleted,isDeleted);
        }
        //查询
        messageMapper.selectPage(messagePage,wrapper);
    }

    @Override
    public void deleteByIds(List<String> id) {
        messageMapper.updateNo(id);
    }
}

