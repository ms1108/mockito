package com.mockito.lesson03;

import com.mockito.common.AccountDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
//通过注解运行
public class MockByAnnotationTest {

    @Mock(answer = Answers.RETURNS_SMART_NULLS)//修改默认返回
    private AccountDao accountDao;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void test() {
        System.out.println(accountDao.findAccount("1", "2"));
    }
}
