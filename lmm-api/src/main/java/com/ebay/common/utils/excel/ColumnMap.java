package com.ebay.common.utils.excel;

import lombok.Data;

/**
 * Created by ldm on 2017/5/31.
 */
@Data
public class ColumnMap {
    private String key;
    private String name;
    private String dateFormat;

    public ColumnMap(String key) {
        this.dataInit(key, key, null);
    }

    public ColumnMap(String key, String name) {
        this.dataInit(key, name, null);
    }

    public ColumnMap(String key, String name, String dateFormat) {
        this.dataInit(key, name, dateFormat);
    }

    private void dataInit(String key, String name, String dateFormat) {
        this.key = key;
        this.name = name;
        this.dateFormat = dateFormat;
    }
}
