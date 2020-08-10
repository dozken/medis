package io.mersys.medis.security.service;


public interface TokenService {

    String getToken(String username, String password);
}
