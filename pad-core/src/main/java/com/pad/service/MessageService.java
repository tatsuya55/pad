package com.pad.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pad.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 留言表 服务类
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
public interface MessageService extends IService<Message> {
    //留言列表分页显示
    void pageQuery(Page<Message> messagePage, Message message);

    //根据留言id删除留言
    void deleteByIds(List<String> id);
}
