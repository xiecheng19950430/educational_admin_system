package com.ebay.core;

import java.lang.annotation.*;

/**
 * @author: jf <for1988@126.com>
 * @date: 2016/12/21
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoSign {
}
