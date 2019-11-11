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
public class MySqlAccountRepository implements AccountRepository {

    private AccountRowMapper accountRowMapper = new AccountRowMapper();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Account> findAll() {
        return jdbcTemplate.query("select * from ACCOUNTS", accountRowMapper);
    }

    @Override
    public Account find(int id) {
        try {
            return jdbcTemplate.queryForObject(String.format(
                    "select * from ACCOUNTS where id = %d", id), accountRowMapper);
        } catch (Exception e) {
            throw new EntityNotFoundException(String.format(
                    "Account with ID = %d does not exist", id));
        }
    }

    @Override
    public List<Account> findAllByName(String name) {
        try {
            return jdbcTemplate.query(
                    "select * from ACCOUNTS where name like '%@name%'".replaceAll("@name", name), accountRowMapper);
        } catch (Exception e) {
            throw new EntityNotFoundException(
                    String.format("Account with NAME = %s does not exist", name));
        }
    }

    @Override
    public List<Account> search(String regex) {
        try {
            return jdbcTemplate.query(
                    "select * from ACCOUNTS where name REGEXP '" + regex + "'", accountRowMapper);
        } catch (Exception e) {
            throw new EntityNotFoundException(
                    String.format("Account with NAME = %s does not exist", regex));
        }
    }


    @Override
    public void store(Account account) {
        String name = account.getName();
        Float money = account.getMoney();
        String iban = account.getIban();
        jdbcTemplate.update(
                "INSERT INTO ACCOUNTS (name, money, iban) VALUES (?, ?, ?)", name, money, iban);
    }

    @Override
    public void update(int id, Account account) {
        String name = account.getName();
        Float money = account.getMoney();
        String iban = account.getIban();
        jdbcTemplate.update(
                "UPDATE ACCOUNTS SET name = ?, money = ?, iban = ? WHERE id = ?", name, money, iban, id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM ACCOUNTS WHERE id = " + id);
    }

    @Override
    public void reset(Account acc) {
        int id = acc.getId();
        jdbcTemplate.update("UPDATE ACCOUNTS SET money = 0 WHERE id = " + id);
        jdbcTemplate.update("DELETE FROM PAYMENTS WHERE id = " + id);
    }
}
