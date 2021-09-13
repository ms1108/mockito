package com.mockito.lesson03;

import com.mockito.common.Account;
import com.mockito.common.AccountDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class MockByRunnerTest {
    @Test//mock会默认返回空对象或者空容器
    public void testMock(){
        AccountDao mock = mock(AccountDao.class);
        Account account = mock.findAccount("x", "xx");
        System.out.println(account);
    }
    @Test//修改默认返回
    public void testMock1(){
        AccountDao mock = mock(AccountDao.class, Mockito.RETURNS_SMART_NULLS);
        Account account = mock.findAccount("x", "xx");
        System.out.println(account);
    }
}
