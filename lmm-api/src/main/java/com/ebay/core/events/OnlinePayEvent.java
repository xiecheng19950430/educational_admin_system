package com.ebay.core.events;

/**
 * @author: jf <for1988@126.com>
 * @date: 2016/12/31
 */
public class OnlinePayEvent extends Event {
    public OnlinePayEvent(){
        super();
    }
    public OnlinePayEvent(Integer uid, String target) {
        super(uid, target, "USER_ONLINE_PAY");
    }
}
