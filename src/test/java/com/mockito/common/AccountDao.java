package com.mockito.common;
//代替不可用的DB
public class AccountDao {
    public Account findAccount(String name,String pwd) {
        throw new UnsupportedOperationException();
    }
}
