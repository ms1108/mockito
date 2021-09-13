package com.mockito.demo;

import com.mockito.common.Account;
import com.mockito.common.AccountDao;
import com.mockito.common.AccountLoginController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountLoginTest {
    private AccountDao accountDao;
    private HttpServletRequest request;
    private AccountLoginController accountLoginController;

    @Before
    public void setUp() {
        this.accountDao = Mockito.mock(AccountDao.class);
        this.request = Mockito.mock(HttpServletRequest.class);
        this.accountLoginController = new AccountLoginController(accountDao);
    }

    @Test
    public void testLoginSuccess() {
        Account account = new Account();
        when(request.getParameter("name")).thenReturn("alex");
        when(request.getParameter("pwd")).thenReturn("123456");
        when(accountDao.findAccount(anyString(),anyString())).thenReturn(account);
        String result = accountLoginController.login(request);
        assertThat(result,equalTo("/index"));
    }
    @Test
    public void testLoginFail() {
        when(request.getParameter("name")).thenReturn("alex");
        when(request.getParameter("pwd")).thenReturn("123456");
        when(accountDao.findAccount(anyString(),anyString())).thenReturn(null);
        String result = accountLoginController.login(request);
        assertThat(result,equalTo("/login"));
    }

    @Test
    public void testLogin505() {
        when(request.getParameter("name")).thenReturn("alex");
        when(request.getParameter("pwd")).thenReturn("123456");
        when(accountDao.findAccount(anyString(),anyString())).thenThrow(UnsupportedOperationException.class);
        String result = accountLoginController.login(request);
        assertThat(result,equalTo("/505"));
    }
}
