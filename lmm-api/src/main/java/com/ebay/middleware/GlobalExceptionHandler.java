package com.ebay.middleware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ebay.common.Errors;
import com.ebay.common.Result;
import com.ebay.core.CommonException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: jf <for1988@126.com>
 * @date: 2016/12/6
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result bindErrorHandler(HttpServletRequest req, BindException e) throws Exception {
        return Result.fail(Errors.VALID_FAIL, e.getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public Result dbErrorHandler(HttpServletRequest req, DataIntegrityViolationException e) {
        if (e.getMessage().indexOf("Data too long") > -1)
            return Result.fail(Errors.VALID_FAIL, "您输入的内容太长了");
        else
            return Result.fail(Errors.VALID_FAIL, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        if (e instanceof CommonException) {
            CommonException commonException = (CommonException) e;
            return Result.fail(commonException.getError());
        } else if (e instanceof RuntimeException) {
            logger.error("exception: ", e);
        } else {
            logger.error("exception: ", e);
        }
        return Result.fail(Errors.VALID_FAIL, e.getMessage());
    }


}
