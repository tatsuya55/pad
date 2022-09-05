package com.pad.controller;



import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.pad.entity.CompanyDetail;
import com.pad.response.R;
import com.pad.service.CompanyDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
/*
*定义在类上：@Api
 定义在方法上：@ApiOperation
 定义在参数上：@ApiParam
* */

@Api(tags = "企业用户详细信息")
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


    @ApiOperation("企业用户详情查询接口")
    //按外键查询
    @GetMapping("/findDetailByPK/{id}")
    @ResponseBody
    public R findByPK(@ApiParam(value = "企业用户外键") @PathVariable("id") String cNo){
        System.out.println(service.selectByPK(cNo));
        return R.ok().data("detail",service.selectByPK(cNo));
    }


    //修改
    @ApiOperation("企业用户详情修改")
    @PutMapping("/update")
    public R update(@ApiParam(value = "详细信息") @RequestBody CompanyDetail companyDetail,  Wrapper<CompanyDetail> companyDetailWrapper){
        boolean a= service.update(companyDetail,companyDetailWrapper);
        if (a){
            return R.ok().message("更新成功");
        }
        return R.error().message("更新失败");
    }

}

