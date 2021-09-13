package com.mockito.lesson04;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StubblingTest {
    private List<String> list;

    @Before
    public void init() {
        this.list = mock(ArrayList.class);
    }

    @Test
    public void useStubbling() {
        when(list.get(0)).thenReturn("first");
        assertThat(list.get(0), equalTo("first"));
        when(list.get(anyInt())).thenThrow(new RuntimeException());
        try {
            list.get(1);
            fail();
        } catch (Exception e) {
            assertThat(e, instanceOf(RuntimeException.class));
        }

    }

    @Test
    public void stubblinVoidMethod() {
        //当方法返回为voids时
        doNothing().when(list).clear();
        list.clear();
        //断言被调用次数
        verify(list, times(1)).clear();

        //期望调用方法时返回异常
        try {
            doThrow(new RuntimeException()).when(list).clear();
            list.clear();
            fail();
        } catch (Exception e) {
            assertThat(e, instanceOf(RuntimeException.class));
        }

    }

    @Test
    public void stubblingDoReturn() {
        when(list.get(0)).thenReturn("first");
        doReturn("second").when(list).get(1);
        assertThat(list.get(0), equalTo("first"));
        assertThat(list.get(1), equalTo("second"));
    }

    @Test
    public void iterateStubblind() {
        //第一次返回1，第二次调用返回2
        //when(list.size()).thenReturn(1).thenReturn(2).thenReturn(3);与下等价
        when(list.size()).thenReturn(1, 2, 3);
        assertThat(list.size(), equalTo(1));
        assertThat(list.size(), equalTo(2));
        assertThat(list.size(), equalTo(3));
        assertThat(list.size(), equalTo(3));
    }

    @Test
    public void answer() {
        //根据传入的值进行逻辑处理再返回
        when(list.get(anyInt())).thenAnswer(invocationOnMock -> {
            Integer index = invocationOnMock.getArgumentAt(0, Integer.class);
            return String.valueOf(index * 10);
        });
        assertThat(list.get(0), equalTo("0"));
        assertThat(list.get(999), equalTo("9990"));
    }

    @Test
    public void readCall() {

        StubblingService service = mock(StubblingService.class);
        System.out.println(service.getClass());
        service.getS();
        System.out.println(service.getI());

        when(service.getS()).thenReturn("alex");
        assertThat(service.getS(), equalTo("alex"));
        //调用真正的方法
        when(service.getI()).thenCallRealMethod();
        assertThat(service.getI(), equalTo(10));
    }

    @After
    public void destroy() {
        reset(this.list);
    }
}
