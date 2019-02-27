package com.ebay.common;

/**
 * @author: jf<for1988@126.com>
 * @date: 2016/12/6
 */
public class Errors {

    public static ApiError AUTH_FAIL = new ApiError(2001, "Auth fail");
    public static ApiError UN_KNOWN_ERROR = new ApiError(2000, "server error");
    public static ApiError NOT_LOGIN = new ApiError(2002, "not login");


    public static ApiError ALREADY_EXISTED = new ApiError(2101, "already existed");
    public static ApiError NOT_FOUND = new ApiError(2100, "not found");

    public static ApiError VALID_FAIL = new ApiError(2003, "Valid fail");
}
