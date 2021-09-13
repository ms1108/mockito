package com.mockito.lesson06;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpyingTest {
    @Test
    public void testSpy(){
        List<String> readList = new ArrayList<>();
        //spy用作部分方法的mock，没有stubbling时就调用真实的方法
        //可以只对部分方法进行mock
        List<String> list = spy(readList);
        list.add("Mockito");
        list.add("PowerMockito");
        assertThat(list.get(0),equalTo("Mockito"));
        assertThat(list.get(1),equalTo("PowerMockito"));
        assertThat(list.isEmpty(),equalTo(false));

        //使用了stubbling时才会mock
        when(list.get(0)).thenReturn("Mockito");
        when(list.size()).thenReturn(0);

        when(list.isEmpty()).thenReturn(true);
        assertThat(list.size(),equalTo(0));

    }
}
