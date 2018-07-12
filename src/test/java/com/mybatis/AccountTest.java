package com.mybatis;

import com.sucl.shirosecurity.entity.Account;
import com.sucl.shirosecurity.mapper.AccountMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/configs/spring-context.xml" })
public class AccountTest {
    @Autowired
    private AccountMapper accountMapper;

    @Test
    public void test(){
        List<Account> accounts = accountMapper.getAll();
        System.out.println(accounts);
    }


}
