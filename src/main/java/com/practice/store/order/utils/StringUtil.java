package com.practice.store.order.utils;

import java.util.Random;

public class StringUtil {

    /*
    Random 문자열 생성
     */
    public static String genRandomString(int length) {
        String number = "0123456789";
        String alpahbet = "ABCDEFGHIJKMNLOPQRSTUVWXYZ";

        String str = number + alpahbet;
        Random random = new Random();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(str.length());
            sb.append(str.charAt(index));
        }

        return sb.toString();
    }
}
