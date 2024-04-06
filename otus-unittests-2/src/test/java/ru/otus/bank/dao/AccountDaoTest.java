package ru.otus.bank.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.bank.entity.Account;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountDaoTest {

    @Test
    public void testFindByAgreementIdCheckEmpty(){
        AccountDao accountDao = new AccountDao();
        Iterable<Account> iterable = accountDao.findByAgreementId(1L);

        assertNotNull(iterable);
        assertEquals(0, StreamSupport.stream(iterable.spliterator(), false).count());
    }
    @Test
    public void testFindByAgreementIdTakeElem(){

        AccountDao accountDao = new AccountDao();
        Account account1 = mock(Account.class);
        when(account1.getAgreementId()).thenReturn(1L);
        when(account1.getId()).thenReturn(3L);
        Account account2 = mock(Account.class);
        when(account2.getAgreementId()).thenReturn(2L);
        when(account2.getId()).thenReturn(4L);

        accountDao.save(account1);
        accountDao.save(account2);

        Iterable<Account> iterable = accountDao.findByAgreementId(1L);

        assertEquals(1, StreamSupport.stream(iterable.spliterator(), false).count());
        assertEquals(3L, StreamSupport.stream(iterable.spliterator(), false).findFirst().get().getId());
    }

    @Test
    public void testFindByIdEmpty(){
        AccountDao accountDao = new AccountDao();
        Optional<Account> account = accountDao.findById(1L);

        assertNotNull(account);
        assertEquals(Optional.empty(), account);
    }

    @Test
    public void testFindByIdTakeElem(){
        AccountDao accountDao = new AccountDao();
        Account account1 = mock(Account.class);
        when(account1.getAgreementId()).thenReturn(1L);
        when(account1.getId()).thenReturn(3L);
        Account account2 = mock(Account.class);
        when(account2.getAgreementId()).thenReturn(2L);
        when(account2.getId()).thenReturn(4L);

        accountDao.save(account1);
        accountDao.save(account2);

        Optional<Account> account = accountDao.findById(3L);
        assertEquals(1L, account.get().getAgreementId());
    }

    @Test
    public void testSeveGiveNewId(){

        AccountDao accountDao = new AccountDao();
        Account account1 = new Account();
        account1.setAgreementId(1L);
        Account account2 = new Account();
        account2.setAgreementId(2L);

        accountDao.save(account1);
        accountDao.save(account2);

        Account account1saved = StreamSupport.stream(accountDao.findByAgreementId(1L).spliterator(), false).findFirst().get();
        Account account2saved = StreamSupport.stream(accountDao.findByAgreementId(2L).spliterator(), false).findFirst().get();

        assertEquals(account1saved.getId() + 1, account2saved.getId());
    }

    @Test
    public void testFindAll(){
        AccountDao accountDao = new AccountDao();
        Account account1 = new Account();
        account1.setAgreementId(1L);
        Account account2 = new Account();
        account2.setAgreementId(2L);

        accountDao.save(account1);
        accountDao.save(account2);

        assertEquals(2, StreamSupport.stream(accountDao.findAll().spliterator(), false).count());

    }
}
