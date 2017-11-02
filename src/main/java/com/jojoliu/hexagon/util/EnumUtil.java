//package com.jojoliu.hexagon.util;
//
//import com.jojoliu.hexagon.enums.OrderStatus;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by Jojo on 25/05/2017.
// */
//public class EnumUtil {
//    public static Map<String, String> queryEnum(String enumName) {
//        Map<String, String> map = new HashMap<>();
//
//        switch (enumName) {
//            case "orderStatus":
//                for (OrderStatus orderStatus : OrderStatus.values()) {
//                    map.put(orderStatus.name(), orderStatus.getDescription());
//                }
//                break;
//            default:
//        }
//
//        return map;
//    }
//}
