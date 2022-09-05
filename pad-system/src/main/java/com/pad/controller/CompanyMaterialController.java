package com.pad.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.github.pagehelper.PageInfo;
import com.pad.entity.CompanyMaterial;
import com.pad.response.R;
import com.pad.service.CompanyMaterialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 企业用户材料信息表 前端控制器
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Api(tags = "企业用户上传材料信息")
@RestController
@RequestMapping("/company-material")
public class CompanyMaterialController {
    @Autowired
    private CompanyMaterialService service;

/*
    //模糊查询
    @ApiOperation("企业用户材料查询接口")
    @PostMapping("findBy")
    public R findByComplex(@ApiParam(value = "材料信息") @RequestBody CompanyMaterial companyMaterial,
                           @RequestParam(value = "pageNum",required = false,defaultValue = "1")Integer pageNum,
                           @RequestParam(value = "pageSize",required = false,defaultValue = "5") Integer pageSize){
        PageInfo<CompanyMaterial> Info=service.findByComplex(pageNum,pageSize,companyMaterial);
        return R.ok().data("Info",Info);
    }*/


    //修改
    @ApiOperation("材料修改审批接口")
    @PutMapping("update")
    public R update(@ApiParam(value = "材料信息") @RequestBody CompanyMaterial companyMaterial, Wrapper<CompanyMaterial> companyMaterialWrapper){
        boolean a= service.update(companyMaterial,companyMaterialWrapper);
        if (a){
            return R.ok().message("更新成功");
        }
        return R.error().message("更新失败");
    }


    @ApiOperation("企业用户材料查询接口")
    //按外键查询
    @GetMapping("/findMaterialByPK/{id}")
    @ResponseBody
    public R findByPK(@ApiParam(value = "企业用户外键") @PathVariable("id") String cNo){
        System.out.println(service.selectByPK(cNo));
        return R.ok().data("material",service.selectByPK(cNo));
    }

}

