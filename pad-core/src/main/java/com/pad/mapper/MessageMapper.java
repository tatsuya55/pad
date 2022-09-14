package com.pad.mapper;

import com.pad.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 留言表 Mapper 接口
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
public interface MessageMapper extends BaseMapper<Message> {

    void updateNo(List<String> id);

    int updateMe(Message message);
}
