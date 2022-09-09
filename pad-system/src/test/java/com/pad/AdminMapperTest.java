package com.pad;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pad.entity.Admin;
import com.pad.entity.AdminRole;
import com.pad.mapper.AdminMapper;
import com.pad.service.AdminRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = PadApplication.class)
public class AdminMapperTest {
    @Autowired
    AdminMapper adminMapper;

    @Autowired
    AdminRoleService adminRoleService;

    @Test
    public void remove(){
        String[] ids = {"1566755754491858946", "1566711476608647170"};
        //删除用户角色
        adminRoleService.remove(
                new LambdaQueryWrapper<AdminRole>()
                .in(AdminRole::getAdminId, Arrays.asList(ids)));
    }

    @Test
    public void del(){
        String[] ids = {"1566755754491858946", "1566711476608647170"};
        adminMapper.deleteAdminByIds(Arrays.asList(ids));
    }

    @Test
    public void test(){
        Admin admin = adminMapper.selectById("1");
        System.out.println(admin);
    }

    @Test
    public void permissionListTest(){
        List<String> permissionList = adminMapper.selectPerValueByUserId("1");
        System.out.println(permissionList);
    }
}
