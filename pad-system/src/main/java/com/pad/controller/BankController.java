package com.pad.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pad.entity.Admin;
import com.pad.entity.AdminRole;
import com.pad.entity.Bank;
import com.pad.entity.Role;
import com.pad.response.R;
import com.pad.service.BankService;
import com.pad.utils.SecurityUtils;
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
 * 合作银行表bank 前端控制器
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Api(tags = "银行管理")
@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private BankService bankService;


    @PreAuthorize("@me.hasAuthority('system:bank:list')")
    @ApiOperation("银行列表分页显示")
    @PostMapping("/list/{current}/{limit}")
    public R adminListPage(
            @ApiParam(name = "current",value = "当前页",required = true)
            @PathVariable long current,
            @ApiParam(name = "limit",value = "每页记录数",required = true)
            @PathVariable long limit,
            @ApiParam(name = "Bank",value = "查询条件",required = false)
            @RequestBody(required = false) Bank bank
    ){
        //创建page对象
        Page<Bank> bankPage = new Page<>(current, limit);
        //查询条件封装在service中
        bankService.pageQuery(bankPage,bank);
        //获取分页后的列表和总记录数
        List<Bank> bankList = bankPage.getRecords();
        long total = bankPage.getTotal();
        return R.ok().data("total",total).data("bankList",bankList);
    }


    @PreAuthorize("@me.hasAuthority('system:bank:query')")
    @ApiOperation("根据id查询银行")
    @GetMapping("/{id}")
    public R getBankById(
            @ApiParam(name = "id",value = "银行编号",required = true)
            @PathVariable String id
    ){
        Bank bank = bankService.getById(id);
        return R.ok().data("bank",bank);
    }

    @PreAuthorize("@me.hasAuthority('system:bank:remove')")
    @ApiOperation("根据id删除银行列表")
    @DeleteMapping("/{id}")
    public R removeBank(
            @ApiParam(name = "id",value = "要删除的银行id",required = true)
            @PathVariable String[] id
    ){
        //根据银行id删除银行
        bankService.deleteByIds(Arrays.asList(id));
        return R.ok().message("删除成功");
    }



    @PreAuthorize("@me.hasAuthority('system:bank:add')")
    @ApiOperation("添加银行列表")
    @PostMapping("/add")
    public R addAdmin(
            @ApiParam(name = "bank",value = "添加的银行",required = true)
            @RequestBody Bank bank
    ){
        //添加用户
        bankService.save(bank);
        return R.ok().message("添加成功");
    }
}

