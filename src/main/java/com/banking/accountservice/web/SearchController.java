package com.banking.accountservice.web;

import com.banking.accountservice.dto.Account;
import com.banking.accountservice.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    SearchService searchService;

    // TODO: Search
    public List<Account> search(@RequestParam String regex) {
        return searchService.search(regex);
    }
}
