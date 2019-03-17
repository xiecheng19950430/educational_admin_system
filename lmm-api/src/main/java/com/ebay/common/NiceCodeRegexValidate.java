package com.ebay.common;

import org.springframework.util.Assert;
import com.ebay.common.utils.RegexUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ldm on 2017/7/10.
 */
public class NiceCodeRegexValidate {
    private static List<String> levitPatterns;

    static synchronized private void init() {
        if (levitPatterns == null) {
            levitPatterns = new ArrayList<String>();
        } else {
            return;
        }
        // 手机号、生日号、跟公司业务相关的号码
//        levitPatterns.add("^(0|13|15|18|168|400|800)[0-9]*$");
//        levitPatterns.add("^\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$");
        levitPatterns.add("^\\d*(1688|2688|2088|2008|5188|10010|10001|666|888|668|686|688|866|868|886|999)\\d*$");
        // 重复号码，镜子号码
        levitPatterns.add("^(\\d)(\\d)(\\d)\\1\\2\\3$");
        levitPatterns.add("^(\\d)(\\d)(\\d)\\3\\2\\1$");
        // AABB
        levitPatterns.add("^\\d*(\\d)\\1(\\d)\\2\\d*$");
        // AAABBB
        levitPatterns.add("^\\d*(\\d)\\1\\1(\\d)\\2\\2\\d*$");
        // ABABAB
        levitPatterns.add("^(\\d)(\\d)\\1\\2\\1\\2\\1\\2$");
        // ABCABC
        levitPatterns.add("^(\\d)(\\d)(\\d)\\1\\2\\3$");
        // ABBABB
        levitPatterns.add("^(\\d)(\\d)\\2\\1\\2\\2$");
        // AABAAB
        levitPatterns.add("^(\\d)\\1(\\d)\\1\\1\\2$");

        // 4-8 位置重复
        levitPatterns.add("^\\d*(\\d)\\1{2,}\\d*$");
        // 4位以上 位递增或者递减（7890也是递增）
        levitPatterns.add("(?:(?:0(?=1)|1(?=2)|2(?=3)|3(?=4)|4(?=5)|5(?=6)|6(?=7)|7(?=8)|8(?=9)|9(?=0)){2,}|(?:0(?=9)|9(?=8)|8(?=7)|7(?=6)|6(?=5)|5(?=4)|4(?=3)|3(?=2)|2(?=1)|1(?=0)){2,})\\d");

        // 不能以 518 、918 结尾
        levitPatterns.add("^[0-9]*(518|918)$");
    }

    public boolean isAllow(String input) {
        Assert.notNull(input);
        return !RegexUtil.contains(input, levitPatterns);
    }

    static {
        init();
    }
}
