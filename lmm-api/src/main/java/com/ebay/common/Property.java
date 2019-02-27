package com.ebay.common;

import lombok.Data;

/**
 * Created by ldm on 2017/6/7.
 */
public class Property {
    private static Config config;

    public static Config getConfig() {
        if (config == null)
            config = new Config();
        return config;
    }

    @Data
    public static class Config {
        private String defaultHead = "https://image.hongheyu1688.com/head_none_bg.png";
        private String defaultBrandLogo = "";
    }
}
