package com.ebay.core.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author: jf <for1988@126.com>
 * @date: 2016/12/31
 */

@Component
public class NewUserEventHandler implements EventHandler<NewUserEvent> {

    private Logger logger = LoggerFactory.getLogger(NewUserEventHandler.class);

    /*@Autowired
    private CouponService couponService;
    @Autowired
    private MessageService messageService;*/

    @Override
    public void handle(NewUserEvent newUserEvent) {
        Integer uid = newUserEvent.getUid();
        logger.info("new user event handle. uid={}, phone={}", uid, newUserEvent.getTarget());
        /*List<Coupon> coupons = couponService.findByEvent(newUserEvent.getName());
        if (coupons != null && coupons.size() > 0) {
            Coupon coupon = coupons.get(0);
            boolean bool = couponService.pick(uid, coupon.getTicketId());
            addMessage(uid, "恭喜您获得" + coupon.getName(), "恭喜您获得" + coupon.getName() + ", 快去我的优惠券中查看吧～");
            logger.info("grant {} ticket to user:{} {}", newUserEvent.getName(), uid, bool ? "success" : "fail");
        }*/
    }

    /*private void addMessage(Integer userId, String title, String desc) {
        Message message = new Message();
        message.setUserId(userId);
        message.setType("00");
        message.setTopic(title);
        message.setDescription(desc);
        message.setUrl("/me/coupon");
        message.setPushState(1);
        messageService.insert(message);
    }*/
}
