package com.pad.controller;


import com.pad.entity.Address;
import com.pad.response.R;
import com.pad.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 地址表 前端控制器
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Api(tags = "地址管理")
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "返回所有地址列表")
    @GetMapping("/list")
    public R getAddressList(){
        List<Address> list = addressService.list(null);
        return R.ok().data("list",list);
    }

}

