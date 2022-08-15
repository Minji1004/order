package com.practice.store.user.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {

    /*
    문자열을 암호화 한다.
     */
    public static String encryptPassword(String password, String email) throws NoSuchAlgorithmException {
        if (password == null)
            return "";

        byte[] hashValue = null;

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        md.reset();
        md.update(email.getBytes());

        hashValue = md.digest(password.getBytes());

        return new String(Base64.encodeBase64(hashValue));
    }
}
