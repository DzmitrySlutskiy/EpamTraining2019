package com.epam.util;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {

    public static String getSha256(@NonNull String pString) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(pString.getBytes());
            byte[] byteData = digest.digest(pString.getBytes());
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < byteData.length; i++){
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (Exception pE)
        {

        }

        return null;
    }
}
