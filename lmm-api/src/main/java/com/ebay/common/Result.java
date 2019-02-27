package com.ebay.common;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: jf <for1988@126.com>
 * @date: 2016/12/5
 */
public class Result {

    private boolean success;

    private Object data;

    private String message;

    private ApiError error;

    private String md5;


    private Result() {

    }

    private void md5() {
        this.md5 = DigestUtils.md5Hex(JSON.toJSONString(data));
    }

    public static Result success() {
        return success(null);
    }

    public static Result success(Object data) {
        return success(data, null);
    }

    public static Result success(Object data, String message) {
        Result result = new Result();
        result.setData(data);
        result.setMessage(message);
        result.setSuccess(true);
        result.md5();
        return result;
    }

    public static Result fail(String message) {
        return fail(null, message);
    }

    public static Result fail(ApiError error) {
        return fail(error, null);
    }

    public static Result fail(ApiError error, String message) {
        Result result = new Result();
        result.setError(error);
        result.setSuccess(false);
        if (StringUtils.isNoneEmpty(message)) {
            result.setMessage(message);
        } else {
            result.setMessage(error.getMessage());
        }
        return result;
    }

    public static Builder build() {
        return new Builder();
    }

    public static class Builder {
        private Map<String, Object> data;
        private String message;

        public Builder() {
            this.data = new HashMap();
        }

        public Builder put(String key, Object value) {
            this.data.put(key, value);
            return this;
        }

        public Result result(String message) {
            return Result.success(data, message);
        }

        public Result result() {
            return Result.success(data);
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApiError getError() {
        return error;
    }

    public void setError(ApiError error) {
        this.error = error;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

}


