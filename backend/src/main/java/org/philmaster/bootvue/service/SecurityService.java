package org.philmaster.bootvue.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
