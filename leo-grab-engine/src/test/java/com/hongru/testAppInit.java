package com.hongru;

import org.junit.Test;

import java.util.Date;

/**
 * Created by chenhongyu on 2016/10/14.
 */
public class testAppInit {
    @Test
    public void testDefaultDate() {
        Date d = new Date(0);
        System.out.println("d = " + d);
    }

    @Test
    public void testSayGoodNight() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Leo Chen, Good night, Happy new year!!!");
        }
    }
}
