package com.ebay.common.utils;


import org.apache.oro.text.regex.*;

import java.util.Iterator;
import java.util.List;


/**
 * Created by ldm on 2017/7/10.
 */
public class RegexUtil {

    private static PatternCompiler compiler = new Perl5Compiler();

    private static PatternMatcher matcher  = new Perl5Matcher();

    private static Pattern pattern  = null;

    /**
     * 根据正则过滤条件过滤
     *
     * @param input
     * @param patternString
     * @return
     * @throws MalformedPatternException
     */
    public static boolean contains(String input, String patternString) {
        try {
            pattern = compiler.compile(patternString);
            if (matcher.contains(input, pattern)) {
                return true;
            }
        } catch (MalformedPatternException e) {
            return false;
        }
        return false;
    }

    /**
     * 根据批量正则过滤条件过滤
     *
     * @param input
     * @param patternStrings
     * @return
     * @throws MalformedPatternException
     */
    public static boolean contains(String input, List<String> patternStrings) {
        for (Iterator<String> lt = patternStrings.listIterator(); lt.hasNext();) {
            if (contains(input, (String) lt.next())) {
                return true;
            }
            continue;
        }
        return false;
    }
}
