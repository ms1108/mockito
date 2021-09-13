package com.mockito.lesson03;

import com.mockito.common.Account;
import com.mockito.common.AccountDao;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class MockByRuleTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    public AccountDao accountDao;

    @Test
    public void test() {
        //AccountDao mock = mock(AccountDao.class);
        Account account = accountDao.findAccount("1", "2");
        System.out.println(account);
    }
}
