package com.mockito.lesson04;

public class StubblingService {
    public int getI(){
        System.out.println("=====getI=====");
        return 10;
    }
    public String getS(){
        System.out.println("=====getS=====");
        throw  new RuntimeException();
    }
}
