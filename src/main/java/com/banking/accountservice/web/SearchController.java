package com.banking.accountservice.web;

import com.banking.accountservice.dto.Account;
import com.banking.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping()
public class SearchController {

    @Autowired
    AccountService searchService;

    @GetMapping("/search")
    public List<Account> search(@RequestParam String regex) {
        return searchService.findAllByName(regex);
    }
}
