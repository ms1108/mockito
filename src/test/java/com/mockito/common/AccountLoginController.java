package com.mockito.common;

import javax.servlet.http.HttpServletRequest;

public class AccountLoginController {
    private AccountDao accountDao;

    public AccountLoginController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public String login(HttpServletRequest httpServletRequest) {
        String name = httpServletRequest.getParameter("name");
        String pwd = httpServletRequest.getParameter("pwd");
        Account account = null;
        try {
            account = accountDao.findAccount(name, pwd);
            if (account==null){
                return "/login";
            }
            return "/index";
        } catch (Exception e) {
            return "/505";
        }
    }
}
