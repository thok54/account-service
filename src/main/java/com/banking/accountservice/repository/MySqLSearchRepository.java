package com.banking.accountservice.repository;

import com.banking.accountservice.dto.Account;
import com.banking.accountservice.repository.mapper.AccountRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class MySqLSearchRepository implements SearchRepository {

    private AccountRowMapper accountRowMapper = new AccountRowMapper();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Account> search(String regex) {
        //TODO: work with regex
        try {
            return jdbcTemplate.query(
                    "select * from ACCOUNTS where name like '%@regex%'".replaceAll("@regex", regex), accountRowMapper);
        } catch (Exception e) {
            throw new EntityNotFoundException(
                    String.format("Account with NAME = %s does not exist", regex));
        }
    }


    private static int compare(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        int matches = 0;
        while (matcher.find()) {
            matches++;
        }
        return matches;
    }
}
