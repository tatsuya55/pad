package com.pad;

import com.pad.entity.Admin;
import com.pad.mapper.AdminMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = PadApplication.class)
public class AdminMapperTest {
    @Autowired
    AdminMapper adminMapper;

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
