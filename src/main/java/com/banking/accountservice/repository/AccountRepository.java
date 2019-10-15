package com.banking.accountservice.repository;

import com.banking.accountservice.dto.Account;

import java.util.List;

public interface AccountRepository {

    List<Account> findAll();

    Account find(int id);

    List<Account> findAllByName(String name);

    void store(Account account);

    void update(int id, Account account);

    void delete(int id);

    void reset(Account acc);
}
