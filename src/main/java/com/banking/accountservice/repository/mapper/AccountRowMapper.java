package com.banking.accountservice.repository.mapper;


import com.banking.accountservice.dto.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        Float money = rs.getFloat("money");
        String iban = rs.getString("iban");

        Account account = new Account();
        account.setId(id);
        account.setName(name);
        account.setMoney(money);
        account.setIban(iban);

        return account;
    }
}
