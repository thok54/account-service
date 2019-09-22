package com.banking.accountservice.dto;

import java.util.Objects;
import java.util.StringJoiner;

public class Account {

    private int id;
    private String name;
    private float money;
    private String iban;

    public Account() {
    }

    public Account(int id) {
        this.id = id;
    }

    public Account(int id, String name, float money, String iban) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.iban = iban;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public float getMoney() {
        return money;
    }


    public void setMoney(float money) {
        this.money = money;
    }


    public String getIban() {
        return iban;
    }


    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Account.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("money=" + money)
                .add("iban='" + iban + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id &&
                Float.compare(account.money, money) == 0 &&
                Objects.equals(name, account.name) &&
                Objects.equals(iban, account.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, money, iban);
    }
}