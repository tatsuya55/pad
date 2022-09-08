package com.pad.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pad.entity.Admin;
import com.pad.entity.CompanyInfo;
import com.pad.response.R;
import com.pad.service.CompanyInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 企业用户基本信息表company_info 前端控制器
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Api(tags = "企业用户基本信息管理")
@RestController
@RequestMapping("/company-info")
public class CompanyInfoController {

    @Autowired
    private CompanyInfoService companyInfoService;

    @ApiOperation("企业用户基本信息表分页显示")
    @PostMapping("/list/{current}/{limit}")
    public R companyInfoListPage(
            @ApiParam(name = "current",value = "当前页",required = true)
            @PathVariable long current,
            @ApiParam(name = "limit",value = "每页记录数",required = true)
            @PathVariable long limit,
            @ApiParam(name = "companyInfo",value = "查询条件",required = false)
            @RequestBody(required = false) CompanyInfo companyInfo
    ){
        //创建page对象
        Page<CompanyInfo> page = new Page<CompanyInfo>(current, limit);
        //查询条件封装在service中
        companyInfoService.pageQuery(page,companyInfo);
        //获取分页后的列表和总记录数
        List<CompanyInfo> companyInfoList = page.getRecords();
        long total = page.getTotal();
        return R.ok().data("total",total).data("companyInfoList",companyInfoList);
    }

    @ApiOperation("按主键查询每个企业用户基本信息")
    @GetMapping("/findBy/{id}")
    public R findBy(@PathVariable("id") String cNo){
        return R.ok().data("id",companyInfoService.getById(cNo));
    }
}

