package com.pad.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.pad.entity.CompanyDetail;
import com.pad.response.R;
import com.pad.service.CompanyDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 企业用户详细信息表 前端控制器
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@RestController
@RequestMapping("/company-detail")
public class CompanyDetailController {

    @Autowired
    private CompanyDetailService service;

    //按主键查询
    @GetMapping("/find/{id}")
    public R selectByPrimaryKey(@PathVariable("id") String id){
        return R.ok().data("detail",service.getById(id));
    }

    //条件查询
    @PostMapping("/find")
    public R selectList(Wrapper<CompanyDetail> queryWrapper){
        return R.ok().data("queryWrapper",service.selectList(queryWrapper));
    }

    //修改
    @PutMapping("/update")
    public R update(@RequestBody CompanyDetail companyDetail){
        boolean a= service.updateById(companyDetail);
        if (a){
            return R.ok().message("更新成功");
        }
        return R.error().message("更新失败");
    }


}

