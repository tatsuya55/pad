package com.pad.service.impl;

import com.pad.entity.Recognition;
import com.pad.mapper.RecognitionMapper;
import com.pad.service.RecognitionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 人脸识别recognition 服务实现类
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Service
public class RecognitionServiceImpl extends ServiceImpl<RecognitionMapper, Recognition> implements RecognitionService {

}
