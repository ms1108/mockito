package com.mockito.lesson03;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DeepMockTest {
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)//将底层依赖的都mock了
    public Lesson03Service lesson03Service;

    //@Mock//test1的方法
    //private Lesson03 lesson03;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {
        Lesson03 lesson03 = lesson03Service.get();
        lesson03.foo();
    }

    //@Test//stubbling
    //public void test1() {
    //    when(lesson03Service.get()).thenReturn(lesson03);
    //    Lesson03 lesson03 = lesson03Service.get();
    //    lesson03.foo();
    //}

    @Test//stubbling
    public void test2() {
        lesson03Service.get().foo();
    }
}

