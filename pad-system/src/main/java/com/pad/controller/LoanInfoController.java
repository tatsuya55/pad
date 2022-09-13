package com.pad.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pad.entity.CompanyInfo;
import com.pad.entity.LoanInfo;
import com.pad.response.R;
import com.pad.service.LoanInfoService;
import com.pad.entity.LoanInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 贷款信息表 前端控制器
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Api(tags = "贷款管理")
@RestController
@RequestMapping("/loan-info")
public class LoanInfoController {

    @Autowired
    private LoanInfoService loanInfoService;

    @PreAuthorize("@me.hasAuthority('system:loanInfo:list')")
    @ApiOperation("贷款信息表分页显示")
    @PostMapping("/list/{current}/{limit}")
    public R loanInfoListPage(
            @ApiParam(name = "current",value = "当前页",required = true)
            @PathVariable long current,
            @ApiParam(name = "limit",value = "每页记录数",required = true)
            @PathVariable long limit,
            @ApiParam(name = "companyInfo",value = "查询条件",required = false)
            @RequestBody(required = false) LoanInfo LoanInfo
    ){
        //创建page对象
        Page<LoanInfo> page = new Page<>(current, limit);
        //查询条件封装在service中
        loanInfoService.pageQuery(page,LoanInfo);
        //获取分页后的列表和总记录数
        List<LoanInfo> loanInfoList = page.getRecords();
        long total = page.getTotal();
        return R.ok().data("total",total).data("loanInfoList",loanInfoList);
    }

    @PreAuthorize("@me.hasAuthority('system:loanInfo:remove')")
    @ApiOperation("根据编号删除贷款表信息")
    @DeleteMapping("/{id}")
    public R deleteLoanInfoByIds(
            @ApiParam(name = "id",value = "要删除的企业用户编号",required = true)
            @PathVariable String[] id
    ){
        List<String> asList = Arrays.asList(id);
        //逻辑删除贷款表信息
        loanInfoService.deleteLoanInfoByIds(asList);
        return R.ok().message("删除成功");
    }

    @PreAuthorize("@me.hasAuthority('system:loanInfo:query')")
    @ApiOperation("按主键查询每个贷款信息")
    @GetMapping("/findBy/{id}")
    public R findBy(@PathVariable("id") String id){
        return R.ok().data("id",loanInfoService.findById(id));
    }
}

