package com.ebay.core.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: jf <for1988@126.com>
 * @date: 2016/12/31
 */

@Component
public class EventDispatcher {

    @Autowired
    private NewUserEventHandler newUserEventHandler;
    /*@Autowired
    private OnlinePayEventHandler onlinePayEventHandler;
    @Autowired
    private QrPayEventHandler qrPayEventHandler;*/

    public void dispatch(Event event) {
        if (event instanceof NewUserEvent) {
            newUserEventHandler.handle((NewUserEvent) event);
        } /*else if (event instanceof OnlinePayEvent) {
            onlinePayEventHandler.handle((OnlinePayEvent) event);
        } else if (event instanceof QrPayEvent) {
            qrPayEventHandler.handle((QrPayEvent) event);
        }*/
    }
}
