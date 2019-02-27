package com.ebay.core;

import com.ebay.common.ApiError;

/**
 * @author: jf <for1988@126.com>
 * @date: 2016/12/7
 */
public class CommonException extends RuntimeException {

    private int code;
    private ApiError error;

    public CommonException(String message) {
        super(message);
    }

    public CommonException(int code, String message) {
        super(message);
        this.code = code;
        this.error = new ApiError(code, message);
    }

    public CommonException(ApiError error) {
        super(error.getMessage());
        this.code = error.getCode();
        this.error = error;
    }

    public ApiError getError() {
        return error;
    }
}
