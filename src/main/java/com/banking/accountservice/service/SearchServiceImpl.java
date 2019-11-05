package com.banking.accountservice.service;

import com.banking.accountservice.dto.Account;
import com.banking.accountservice.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchRepository repository;

    @Override
    public List<Account> search(String regex) {
        return repository.search(regex);
    }
}
