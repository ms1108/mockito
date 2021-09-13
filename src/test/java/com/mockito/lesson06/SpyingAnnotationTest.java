package com.mockito.lesson06;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class SpyingAnnotationTest {
    @Spy
    private List<String> list = new ArrayList<>();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {
        list.add("Mockito");
        list.add("PowerMockito");
        assertThat(list.get(0), equalTo("Mockito"));
        assertThat(list.get(1), equalTo("PowerMockito"));
        assertThat(list.isEmpty(), equalTo(false));

        //使用了stubbling时才会mock
        when(list.get(0)).thenReturn("Mockito");
        when(list.size()).thenReturn(0);

        when(list.isEmpty()).thenReturn(true);
        assertThat(list.size(), equalTo(0));
    }
}
