package com.ebay.common.utils;

import java.util.Map;

/**
 * Created by ldm on 2017/9/21.
 */
public class MapUtil {

    public static <K, V> V getValue(Map<K, V> map, K key) {
        return map.containsKey(key) ? map.get(key) : null;
    }
}
