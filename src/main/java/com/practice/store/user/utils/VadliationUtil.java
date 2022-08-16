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

    /*
    str이 영어로만 구성되어 있으면 true
     */
    public static boolean isEnglish(String str) {
        if(Pattern.compile("^[a-zA-Z]*$").matcher(str).find()) {
            return true;
        }

        return false;
    }

    /*
    str이 password 규칙에 맞으면 true
    비밀번호는 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함, 8자리 이상
     */
    public static boolean isPassword(String str) {
        if(Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]{8,}$").matcher(str).find()) {
            return true;
        }

        return  false;
    }
}
