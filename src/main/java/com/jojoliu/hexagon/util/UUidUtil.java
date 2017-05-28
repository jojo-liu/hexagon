package com.jojoliu.hexagon.util;

import java.util.UUID;

/**
 * Created by Jojo on 27/05/2017.
 */
public class UUidUtil {
    public static String generate() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
