package com.pad;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pad.entity.Admin;
import com.pad.entity.AdminRole;
import com.pad.mapper.AdminMapper;
import com.pad.service.AdminRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = PadApplication.class)
public class AdminMapperTest {
    @Autowired
    AdminMapper adminMapper;

    @Autowired
    AdminRoleService adminRoleService;

    @Test
    public void remove(){
        //删除用户角色
        adminRoleService.remove(
                new LambdaQueryWrapper<AdminRole>()
                .eq(AdminRole::getAdminId,"1566755754491858946"));
    }


    @Test
    public void test(){
        Admin admin = adminMapper.selectById("1");
        System.out.println(admin);
    }

    @Test
    public void permissionListTest(){
        List<String> permissionList = adminMapper.selectPermissionByUserId("1");
        System.out.println(permissionList);
    }
}
