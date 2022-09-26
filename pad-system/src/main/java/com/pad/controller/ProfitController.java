package com.pad.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Month;
import cn.hutool.core.date.Quarter;
import com.pad.entity.CompanyInfo;
import com.pad.entity.Profit;
import com.pad.response.R;
import com.pad.service.ProfitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 平台收益表profit 前端控制器
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Api(tags = "平台收益管理")
@RestController
@RequestMapping("/profit")
public class ProfitController {

    @Autowired
    private ProfitService profitService;

    @ApiOperation("查询平台每月收益的金额")
    @GetMapping("/profitData")
    public R members() {
        List<Profit> list = profitService.list(null);
        int february = 0; // 第一月
        int march = 0;
        int april = 0;
        int may = 0;
        int june = 0;
        int july = 0;
        int august = 0;
        int september = 0;
        int october = 0;
        int november = 0;
        int december = 0;
        int undecimber = 0;
        for (Profit profit : list) {
            int month = profit.getCreateTime().getMonth();
            month+=1;
            switch (month){
                case 1: february += profit.getValue(); break;
                case 2: march += profit.getValue(); break;
                case 3: april += profit.getValue(); break;
                case 4: may += profit.getValue(); break;
                case 5: june += profit.getValue(); break;
                case 6: july += profit.getValue(); break;
                case 7: august += profit.getValue(); break;
                case 8: september += profit.getValue(); break;
                case 9: october += profit.getValue(); break;
                case 10: november += profit.getValue(); break;
                case 11: december += profit.getValue(); break;
                case 12: undecimber += profit.getValue(); break;
                default: break;
            }
        }
        return R.ok().data("profitData", CollUtil.newArrayList(february,march,april,may,june,july,august,september,october,november,december,undecimber));
    }
}

