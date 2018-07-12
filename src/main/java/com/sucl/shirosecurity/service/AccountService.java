package com.sucl.shirosecurity.service;

import com.sucl.shirosecurity.entity.Account;
import com.sucl.shirosecurity.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountMapper accountMapper;

    public Account getAcountByLoginname(String loginname){
        return accountMapper.getAccountByLoginname(loginname);
    }
}
