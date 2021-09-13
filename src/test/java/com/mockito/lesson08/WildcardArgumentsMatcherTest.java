package com.mockito.lesson08;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WildcardArgumentsMatcherTest {
    @Mock
    private SimpleService simpleService;

    @Test
    public void wildcardMethod() {
        when(simpleService.method1(anyInt(), anyString(), anyCollection(), isA(Serializable.class))).thenReturn(100);
        int result = simpleService.method1(1, "a", Collections.emptyList(), "mockito");
        assertThat(result, equalTo(100));

        result = simpleService.method1(1, "a", Collections.emptyList(), "mockito1");
        assertThat(result, equalTo(100));
    }

    @Test
    public void wildcardMethodWithSpec() {
        //incorrect:
        //someMethod(anyObject(), "raw String");
        //when(simpleService.method1(anyInt(), "Alex", anyCollection(), isA(Serializable.class))).thenReturn(100);
        //when(simpleService.method1(anyInt(), "Wang", anyCollection(), isA(Serializable.class))).thenReturn(200);

        when(simpleService.method1(anyInt(), anyString(), anyCollection(), isA(Serializable.class))).thenReturn(-1);
        when(simpleService.method1(anyInt(), eq("Alex"), anyCollection(), isA(Serializable.class))).thenReturn(100);
        when(simpleService.method1(anyInt(), eq("Wang"), anyCollection(), isA(Serializable.class))).thenReturn(200);
        //anyString会覆盖上面d的
        //when(simpleService.method1(anyInt(), anyString(), anyCollection(), isA(Serializable.class))).thenReturn(-1);

        int result = simpleService.method1(1, "Alex", Collections.emptyList(), "mockito");
        assertThat(result, equalTo(100));

        result = simpleService.method1(1, "Wang", Collections.emptyList(), "mockito1");
        assertThat(result, equalTo(200));

        result = simpleService.method1(1, "111", Collections.emptyList(), "mockito1");
        assertThat(result, equalTo(-1));
    }

    @Test
    public void wildcardMethod2() {
        List<Object> list = Collections.emptyList();
        doNothing().when(simpleService).method2(anyInt(), anyString(), anyCollection(), isA(Serializable.class));
        simpleService.method2(1, "a", list, "mockito");
        verify(simpleService, times(1)).method2(1, "a", list, "mockito");
        verify(simpleService, times(1)).method2(anyInt(), eq("a"), anyCollection(), isA(Serializable.class));
    }

    @After
    public void destroy() {
        reset(simpleService);
    }
}
