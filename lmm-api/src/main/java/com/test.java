package com;

import com.ebay.common.utils.Station;

import java.net.URL;

public class test {

    public static void main(String[] args) {
//        Station station1 = new Station("窗口1");
//        Station station2 = new Station("窗口2");
//        Station station3 = new Station("窗口3");
//
//        station1.start();
//        station2.start();
//        station3.start();
        Object object = new Object();
        URL url = object.getClass().getResource("/properties/basecom.properties");
        System.out.println(url.toString());
    }

}
