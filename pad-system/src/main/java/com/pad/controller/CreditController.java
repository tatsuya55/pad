package com.pad.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pad.entity.Credit;
import com.pad.entity.LoanInfo;
import com.pad.response.R;
import com.pad.service.CreditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 放款表 前端控制器
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Api(tags = "放款管理")
@RestController
@RequestMapping("/credit")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @PreAuthorize("@me.hasAuthority('credit:info:list')")
    @ApiOperation("放款信息表分页显示")
    @PostMapping("/list/{current}/{limit}")
    public R creditListPage(
            @ApiParam(name = "current",value = "当前页",required = true)
            @PathVariable long current,
            @ApiParam(name = "limit",value = "每页记录数",required = true)
            @PathVariable long limit,
            @ApiParam(name = "credit",value = "查询条件",required = false)
            @RequestBody(required = false) Credit credit
    ){
        //创建page对象
        Page<Credit> page = new Page<>(current, limit);
        //查询条件封装在service中
        creditService.pageQuery(page,credit);
        //获取分页后的列表和总记录数
        List<Credit> creditList = page.getRecords();
        long total = page.getTotal();
        return R.ok().data("total",total).data("creditList",creditList);
    }
}

