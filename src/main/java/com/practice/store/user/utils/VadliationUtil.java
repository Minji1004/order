package com.practice.store.user.utils;

import java.util.regex.Pattern;

public class VadliationUtil {

    /*
    str이 length 보다 짧은 경우 true
     */
    public static boolean isShorterLength(String str, int length) {
        if(str.length() < length) {
            return true;
        }

        return false;
    }

    /*
    str이 length 보다 긴 경우 true
     */
    public static boolean isLongerLength(String str, int length) {
        if(str.length() > length) {
            return true;
        }

        return false;
    }

    /*
    str이 숫자로만 이루어져있으면 true
     */
    public static boolean isNumber(String str) {
        if(Pattern.compile("^[0-9]*$").matcher(str).find()) {
            return true;
        }

        return false;
    }

    /*
    str에 한글이나 영어로만 구성되어 있으면 true
     */
    public static boolean isEnglishAndKorean(String str) {
        if(Pattern.compile("^[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]*$").matcher(str).find()) {
            return true;
        }

        return false;
    }

    /*
    str이 email 규격에 맞으면 true
     */
    public static boolean isEmail(String str) {
        if(Pattern.compile("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$").matcher(str).find()) {
            return true;
        }

        return false;
    }
}
