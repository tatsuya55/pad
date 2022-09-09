package com.pad.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pad.entity.CompanyInfo;
import com.pad.response.R;
import com.pad.service.CompanyInfoService;
import com.pad.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PreAuthorize("@me.hasAuthority('system:companyInfo:list')")
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

    @PreAuthorize("@me.hasAuthority('system:companyInfo:query')")
    @ApiOperation("按主键查询每个企业用户基本信息")
    @GetMapping("/findBy/{id}")
    public R findBy(@PathVariable("id") String cNo){
        return R.ok().data("id",companyInfoService.getById(cNo));
    }

    @PreAuthorize("@me.hasAuthority('system:companyInfo:remove')")
    @ApiOperation("根据编号删除企业用户基本信息")
    @DeleteMapping("/{cNo}")
    public R deleteCompanyInfoByIds(
            @ApiParam(name = "cNo",value = "要删除的企业用户编号",required = true)
            @PathVariable String[] cNo
    ){
        List<String> asList = Arrays.asList(cNo);
        //逻辑删除企业用户基本信息
        companyInfoService.deleteCompanyInfoByIds(asList);
        return R.ok().message("删除成功");
    }

    @PreAuthorize("@me.hasAuthority('system:companyInfo:edit')")
    @ApiOperation("修改企业用户基本信息")
    @PutMapping("/edit")
    public R editCompanyInfo(
            @ApiParam(name = "companyInfo",value = "要修改的企业用户基本信息",required = true)
            @RequestBody CompanyInfo companyInfo
    ){
        //更新
        companyInfoService.updateById(companyInfo);
        return R.ok().message("修改企业用户基本信息成功");
    }

    @PreAuthorize("@me.hasAuthority('system:companyInfo:add')")
    @ApiOperation("添加企业用户基本信息")
    @PostMapping("/add")
    public R addCompanyInfo(
            @ApiParam(name = "companyInfo",value = "添加的企业用户基本信息",required = true)
            @RequestBody CompanyInfo companyInfo
    ){
        //密码加密
        String encode = SecurityUtils.encryptPassword(companyInfo.getPassword());
        companyInfo.setPassword(encode);
        //添加
        companyInfoService.save(companyInfo);
        return R.ok().message("添加成功");
    }

    @PreAuthorize("@me.hasAuthority('system:companyInfo:edit')")
    @ApiOperation("根据编号修改企业用户基本信息的认证状态值")
    @PutMapping("/{cNo}")
    public R updateCompanyInfoStatus(
            @ApiParam(name = "cNo",value = "要修改的企业用户编号",required = true)
            @PathVariable String cNo,
            @ApiParam(name = "auth_status",value = "要修改的企业用户基本信息认证状态值",required = true)
            @RequestBody CompanyInfo companyInfo
    ){
        //修改企业用户基本信息的认证状态值
        companyInfoService.updateCompanyInfoStatus(companyInfo.getAuthStatus(),cNo);
        return R.ok().message("修改状态值成功");
    }
}

