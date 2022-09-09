package com.pad.mapper;


import com.pad.entity.CompanyMaterial;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 企业用户材料信息表 Mapper 接口
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Mapper
public interface CompanyMaterialMapper extends BaseMapper<CompanyMaterial> {


    /**
     * 外键查询
     */
    List<CompanyMaterial> selectByFK(String cNo);

}
