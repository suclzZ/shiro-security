package com.sucl.shirosecurity.mapper;

import com.sucl.shirosecurity.entity.Account;

import java.util.List;

public interface AccountMapper {

    Account getAccount(String id);

    List<Account> getAll();

    List<Account> getAccounts(Account account);

    Account getAccountByLoginname(String loginname);

    Account getAcountByTelephone(String telephone);

    Account getAcountByEmail(String email);
}
