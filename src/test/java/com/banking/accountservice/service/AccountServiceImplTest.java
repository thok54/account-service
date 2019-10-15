package com.banking.accountservice.service;

import com.banking.accountservice.dto.Account;
import com.banking.accountservice.repository.MySqlAccountRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {

    private static final String ACCOUNT_NAME = "name";
    private static final int ACCOUNT_ID = 1;
    private static final int INVALID_ACCOUNT_ID = -10;
    private Account expectedAccount = new Account();

    @Mock
    private MySqlAccountRepository repository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testFindAllCallsFindAllFromRepository() {
        accountService.findAll();
        verify(repository).findAll();
    }

    @Test
    public void testFindAccountCallsFindFromRepository() {
        accountService.find(ACCOUNT_ID);
        verify(repository).find(ACCOUNT_ID);
    }

    @Test
    public void testFindAccountReturnsNullIfAccountNotFound() {
        when(repository.find(INVALID_ACCOUNT_ID)).thenReturn(null);
        Account account = accountService.find(INVALID_ACCOUNT_ID);
        assertNull(account);
    }


    @Test
    public void testFindAccountsCallsFindByNameFromRepository() {
        accountService.findAllByName(ACCOUNT_NAME);
        verify(repository).findAllByName(ACCOUNT_NAME);
    }

    @Test
    public void testStoreAccountCallsStoreFromRepository() {
        accountService.store(expectedAccount);
        verify(repository).store(expectedAccount);
    }

    @Test
    public void testUpdateAccountCallsUpdateFromRepository() {
        accountService.update(ACCOUNT_ID, expectedAccount);
        verify(repository).update(ACCOUNT_ID, expectedAccount);
    }

    @Test
    public void testDeleteAccountCallsDeleteFromRepository() {
        accountService.delete(ACCOUNT_ID);
        verify(repository).delete(ACCOUNT_ID);
    }

    @Test
    public void testResetAccountCallsResetFromRepository() {
        accountService.reset(expectedAccount);
        verify(repository).reset(expectedAccount);
    }
}