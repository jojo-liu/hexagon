package com.jojoliu.hexagon.util;

import java.util.Date;

/**
 * Created by Jojo on 27/05/2017.
 */
public class NoUtil {
    public static String generate(String prefix) {
        String no = DateTimeUtil.formatDateTime(new Date(), "yyyyMMddhhmmssSSS");
        int random = (int) (Math.random()*9000+1000);
        no = no + random;
        if (prefix == null || "".equals(prefix)) {
            return no;
        }
        return prefix + no;
    }
}
