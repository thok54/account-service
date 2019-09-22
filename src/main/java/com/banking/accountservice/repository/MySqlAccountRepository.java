package com.banking.accountservice.repository;

import com.banking.accountservice.dto.Account;
import com.banking.accountservice.util.DataBaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MySqlAccountRepository implements AccountRepository {

    @Autowired
    private DataBaseUtil dataBaseUtil;

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList();
        Connection con = dataBaseUtil.startConnection();
        try {
            PreparedStatement pstmt = con.prepareStatement("select * from ACCOUNTS");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                Float money = rs.getFloat("money");
                String iban = rs.getString("iban");
                Account account = new Account(id, name, money, iban);
                accounts.add(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dataBaseUtil.closeConections(con);
        }
        return accounts;
    }

    @Override
    public Account find(int id) {
        Connection con = dataBaseUtil.startConnection();
        try {
            PreparedStatement pstmt = con.prepareStatement(String.format("select * from ACCOUNTS where id = %d", id));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                Float money = rs.getFloat("money");
                String iban = rs.getString("iban");
                return new Account(id, name, money, iban);
            }
            throw new EntityNotFoundException(String.format("Account with ID = %d does not exist", id));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dataBaseUtil.closeConections(con);
        }
        return null;
    }

    @Override
    public List<Account> findAllByName(String name) {
        List<Account> accounts = new ArrayList();
        Connection con = dataBaseUtil.startConnection();
        try {
            PreparedStatement pstmt = con.prepareStatement(String.format("select * from ACCOUNTS where name = %s", name));
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id");
                Float money = rs.getFloat("money");
                String iban = rs.getString("iban");
                Account account = new Account(id, name, money, iban);
                accounts.add(account);
            }
            if (accounts.isEmpty()) {
                throw new EntityNotFoundException(String.format("Account with NAME = %s does not exist", name));
            }
            return accounts;
        } catch (
                Exception e) {
            e.printStackTrace();
        } finally {
            dataBaseUtil.closeConections(con);
        }
        return accounts;
    }

    @Override
    public void store(Account account) {
        Connection con = dataBaseUtil.startConnection();
        try {
            String name = account.getName();
            Float money = account.getMoney();
            String iban = account.getIban();

            String query = String.format("INSERT INTO ACCOUNTS (name, money, iban) VALUES (\"%s\", %f, \"%s\")", name, money, iban);
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dataBaseUtil.closeConections(con);
        }
    }

    @Override
    public void update(int id, Account account) {
        Connection con = dataBaseUtil.startConnection();
        try {
            String name = account.getName();
            Float money = account.getMoney();
            String iban = account.getIban();
            String query = String.format("UPDATE ACCOUNTS SET name = \"%s\", money = %f, iban = \"%s\" WHERE id = %d", name, money, iban, id);
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dataBaseUtil.closeConections(con);
        }
    }

    @Override
    public void delete(int id) {
        Connection con = dataBaseUtil.startConnection();
        try {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM ACCOUNTS WHERE id = " + id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dataBaseUtil.closeConections(con);
        }
    }


    @Override
    public void reset(Account acc) {
        Connection con = dataBaseUtil.startConnection();
        try {
            int id = acc.getId();
            String query1 = "UPDATE ACCOUNTS SET money = 0 WHERE id = " + id;
            PreparedStatement pstmt1 = con.prepareStatement(query1);
            pstmt1.executeUpdate();
            String query2 = "DELETE FROM PAYMENTS WHERE userId = " + id;
            PreparedStatement pstmt2 = con.prepareStatement(query2);
            pstmt2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dataBaseUtil.closeConections(con);
        }
    }
}
