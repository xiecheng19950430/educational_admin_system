package com.ebay.core;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.io.UnsupportedEncodingException;

/**
 * Created by chenliang on 2017/1/11.
 */
public class ShopUserSession {

    private int userId;
    private Integer shopId;
    private String token;

    public ShopUserSession() {
    }

    public ShopUserSession(int userId, Integer shopId, String AppSecret) throws UnsupportedEncodingException {
        this.userId = userId;
        this.token = JWT.create().withClaim("uid", userId)
                .withClaim("shopid", shopId)
                .withClaim("timestamp", Long.valueOf(System.currentTimeMillis()).intValue())
                .sign(Algorithm.HMAC512(AppSecret));
    }

    public ShopUserSession(String token) {
        this.token = token;
        JWT jwt = JWT.decode(token);
        this.userId = jwt.getClaim("uid").asInt();
        this.shopId = jwt.getClaim("shopid").asInt();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
