package com.pad;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest(classes = PadApplication.class)
public class BCryptPasswordEncoderTest {

    @Test
    public void TestBCryptPasswordEncoder(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //加密
        String encode = passwordEncoder.encode("11111");
        System.out.println("加密后的密码为："+encode);
        //比较 传入明文密码与数据库密文密码比较
        boolean matches = passwordEncoder.matches("11111", encode);
        System.out.println("比较的结果为："+matches);
        // 如果解析的密码能够再次进行解析且达到更安全的结果则返回 true，否则返回 false。默认返回 false
        boolean upgradeEncoding = passwordEncoder.upgradeEncoding(encode);
        System.out.println("能否再次进行解析"+upgradeEncoding);
    }
}
