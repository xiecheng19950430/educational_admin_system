package com.ebay.core.events;

/**
 * @author: jf <for1988@126.com>
 * @date: 2017/1/14
 */
public class QrPayEvent extends Event {
    public QrPayEvent(){
        super();
    }
    public QrPayEvent(Integer uid, String target) {
        super(uid, target, "USER_QRPAY");
    }
}
