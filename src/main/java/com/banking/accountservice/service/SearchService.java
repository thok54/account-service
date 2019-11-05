package com.banking.accountservice.service;

import com.banking.accountservice.dto.Account;

import java.util.List;

public interface SearchService {
    List<Account> search(String regex);
}
