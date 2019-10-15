package com.banking.accountservice.repository.mapper;

import com.banking.accountservice.dto.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountRowMapperTest {

    @Mock
    private ResultSet resultSet;

    private AccountRowMapper mapper = new AccountRowMapper();

    @Test
    public void shouldMapRowUsingBank() throws SQLException {
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("a name");
        when(resultSet.getFloat("money")).thenReturn(1.00f);
        when(resultSet.getString("iban")).thenReturn("an iban");

        Account expected = mapper.mapRow(resultSet, 0);

        assertNotNull(expected);
        assertEquals(1, expected.getId());
        assertEquals("a name", expected.getName());
        assertEquals(1.00, expected.getMoney(), 0.00);
        assertEquals("an iban", expected.getIban());
    }
}

