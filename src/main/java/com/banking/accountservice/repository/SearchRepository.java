package com.banking.accountservice.repository;

import com.banking.accountservice.dto.Account;

import java.util.List;

public interface SearchRepository {
    List<Account> search(String name);
}
