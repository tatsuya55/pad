package com.pad;

import com.pad.entity.Admin;
import com.pad.entity.Bank;
import com.pad.mapper.BankMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = PadApplication.class)
public class BankMapperTest {

    @Autowired
    private BankMapper bankMapper;

    @Test
    public void test(){
        Bank bank = bankMapper.selectById("1");
        System.out.println(bank);
    }
}
