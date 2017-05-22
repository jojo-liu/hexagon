package com.jojoliu.hexagon.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by huangzhaolong on 2017/5/16.
 */
public class LoggerFactory {
    public static Logger getInfoLogger() {
        return LogManager.getLogger("info");
    }

    public static Logger getWarnLogger() {
        return LogManager.getLogger("warn");
    }

    public static Logger getErrorLogger() {
        return LogManager.getLogger("error");
    }

}
