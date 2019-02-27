package com.ebay.core.events;

/**
 * @author: jf <for1988@126.com>
 * @date: 2016/12/31
 */
public interface EventHandler<E extends Event> {

    void handle(E e);
}
