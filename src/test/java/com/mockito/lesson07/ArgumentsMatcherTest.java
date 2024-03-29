package com.mockito.lesson07;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

public class ArgumentsMatcherTest {
    @Test
    public void test() {
        List<Integer> list = mock(ArrayList.class);
        when(list.get(eq(0))).thenReturn(100);
        assertThat(list.get(0), equalTo(100));
        assertThat(list.get(1), nullValue());
    }

    @Test
    public void testComplex() {
        Foo foo = mock(Foo.class);
        //传入实例
        //when(foo.function(isA(Parent.class))).thenReturn(100);
        when(foo.function(isA(Child1.class))).thenReturn(100);
        //会失败，没有匹配到
        //when(foo.function(isA(Child2.class))).thenReturn(100);
        int result = foo.function(new Child1());
        assertThat(result, equalTo(100));

        reset(foo);
        //any可以传入任何参数，一路绿灯
        when(foo.function(any(Child1.class))).thenReturn(100);
        result = foo.function(new Child1());
        assertThat(result, equalTo(100));

    }

    static class Foo {
        int function(Parent parent) {
            return parent.work();
        }
    }

    interface Parent {
        int work();
    }

    class Child1 implements Parent{

        @Override
        public int work() {
            throw new RuntimeException();
        }
    }
    class Child2 implements Parent{

        @Override
        public int work() {
            throw new RuntimeException();
        }
    }
}
