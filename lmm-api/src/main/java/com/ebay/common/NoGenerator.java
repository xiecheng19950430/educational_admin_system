package com.ebay.common;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @author: jf <for1988@126.com>
 * @date: 2016/12/20
 */
public class NoGenerator {

    public static String generate() {
        String random = UUID.randomUUID().toString();
        String now = DateUtil.format(new Date(), "yyyyMMddHHmmssSSS");

        String randomNo = String.valueOf(Math.abs(random.hashCode()));

        if (randomNo.length() > 10) {
            randomNo = randomNo.substring(0, 10);
        } else if (randomNo.length() < 10) {
            randomNo = new String(new char[10 - randomNo.length()]).replace("\0", "0") + randomNo;
        }

        return now + randomNo;
    }

    public static String generateDepositNo() {
        String random = UUID.randomUUID().toString();
        String now = String.valueOf(new Date().getTime());

        String randomNo = String.valueOf(Math.abs(random.hashCode()));

        if (randomNo.length() > 10) {
            randomNo = randomNo.substring(0, 10);
        } else if (randomNo.length() < 10) {
            randomNo = new String(new char[10 - randomNo.length()]).replace("\0", "0") + randomNo;
        }

        return now + randomNo;
    }

    //生成随机数字
    public static String getRandomNum(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        return val;
    }
}
