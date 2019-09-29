package com.banking.accountservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Account {

    private int id;
    private String name;
    private float money;
    private String iban;
}