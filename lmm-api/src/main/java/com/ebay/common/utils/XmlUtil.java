package com.ebay.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author: jf <for1988@126.com>
 * @date: 2016/12/26
 */
public class XmlUtil {

    private static Logger logger = LoggerFactory.getLogger(XmlUtil.class);

    public static String toXml(Object o) {
        ObjectMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            logger.error("object to xml error.", e);
        }
        return null;
    }

    public static <T> T toBean(String xml, Class<T> clazz) {
        ObjectMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.readValue(xml, clazz);
        } catch (IOException e) {
            logger.error("xml to object error.", e);
            e.printStackTrace();
        }
        return null;
    }
}
