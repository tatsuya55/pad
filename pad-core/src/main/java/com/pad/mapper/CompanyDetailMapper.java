package com.pad.mapper;

import com.pad.entity.CompanyDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 企业用户详细信息表 Mapper 接口
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Mapper
public interface CompanyDetailMapper extends BaseMapper<CompanyDetail> {

    /**
     * 外键查询
     */
    List<CompanyDetail> selectByFK(String cNo);


    /**
     * 逻辑删除企业用户详细信息
     * @param id
     */
    void updateCompanyDetailByIds(List<String> id);

}
