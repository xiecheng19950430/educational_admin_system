package com.ebay.core.events;

/**
 * @author: jf <for1988@126.com>
 * @date: 2016/12/30
 */
public class NewUserEvent extends Event {
    public NewUserEvent(){
        super();
    }

    public NewUserEvent(Integer uid, String target) {
        super(uid, target, "NEW_USER");
    }
}
