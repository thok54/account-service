package com.banking.accountservice.service;

import com.banking.accountservice.dto.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account find(int accountId);

    List<Account> findAllByName(String name);

    void store(Account acc);

    void update(int id, Account acc);

    void delete(int id);

    void reset(Account acc);
}
