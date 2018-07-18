package com.other;

import java.util.Date;

public class DateTest {

    public static void main(String[] args) {

        long now = System.currentTimeMillis();
        long afterNow = now+1000;

        Date nowDate = new Date(now);

        Date d2 = new Date(afterNow);

        System.out.println(nowDate.before(d2));

    }
}
