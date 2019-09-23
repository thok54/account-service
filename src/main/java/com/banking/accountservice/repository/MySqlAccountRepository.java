package com.banking.accountservice.repository;

import com.banking.accountservice.dto.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MySqlAccountRepository implements AccountRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Account> findAll() {
        return jdbcTemplate.query("select * from ACCOUNTS", new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                Float money = rs.getFloat("money");
                String iban = rs.getString("iban");
                return new Account(id, name, money, iban);
            }
        });
    }

    @Override
    public Account find(int id) {
        try {
            return jdbcTemplate.queryForObject(String.format(
                    "select * from ACCOUNTS where id = %d", id), new RowMapper<Account>() {
                @Override
                public Account mapRow(ResultSet rs, int i) throws SQLException {
                    String name = rs.getString("name");
                    Float money = rs.getFloat("money");
                    String iban = rs.getString("iban");
                    return new Account(id, name, money, iban);
                }
            });
        } catch (Exception e) {
            throw new EntityNotFoundException(String.format(
                    "Account with ID = %d does not exist", id));
        }
    }

    @Override
    public List<Account> findAllByName(String name) {
        try {
            return jdbcTemplate.query(String.format(
                    "select * from ACCOUNTS where name = %s", name), new RowMapper<Account>() {
                @Override
                public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Integer id = rs.getInt("id");
                    Float money = rs.getFloat("money");
                    String iban = rs.getString("iban");
                    return new Account(id, name, money, iban);
                }
            });
        } catch (Exception e) {
            throw new EntityNotFoundException(String.format("Account with NAME = %s does not exist", name));
        }
    }


    @Override
    public void store(Account account) {
        String name = account.getName();
        Float money = account.getMoney();
        String iban = account.getIban();
        jdbcTemplate.update(String.format(
                "INSERT INTO ACCOUNTS (name, money, iban) VALUES (\"%s\", %f, \"%s\")", name, money, iban));
    }

    @Override
    public void update(int id, Account account) {
        String name = account.getName();
        Float money = account.getMoney();
        String iban = account.getIban();
        jdbcTemplate.update(String.format(
                "UPDATE ACCOUNTS SET name = \"%s\", money = %f, iban = \"%s\" WHERE id = %d", name, money, iban, id));
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(String.format("DELETE FROM ACCOUNTS WHERE id = " + id));
    }

    @Override
    public void reset(Account acc) {
        int id = acc.getId();
        jdbcTemplate.update("UPDATE ACCOUNTS SET money = 0 WHERE id = " + id);
        jdbcTemplate.update("DELETE FROM PAYMENTS WHERE id = " + id);
    }
}
