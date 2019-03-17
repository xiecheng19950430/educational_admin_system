package com.ebay.core;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.io.UnsupportedEncodingException;

/**
 * @author: jf <for1988@126.com>
 * @date: 2016/12/7
 */
public class UserSession {
    private int userId;
    private String token;

    public UserSession() {
    }

    public UserSession(int userId, String AppSecret) throws UnsupportedEncodingException {
        this.userId = userId;
        this.token = JWT.create().withClaim("uid", userId)
                .withClaim("timestamp", Long.valueOf(System.currentTimeMillis()).intValue())
                .sign(Algorithm.HMAC512(AppSecret));
    }

    public UserSession(String token) {
        this.token = token;
        JWT jwt = JWT.decode(token);
        this.userId = jwt.getClaim("uid").asInt();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
