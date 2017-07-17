package com.stayrascal.cloud.common.util;

import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.jersey.exception.InternalErrorException;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
    public HashUtil() {
    }

    public static String digestHex(String keychain) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            byte[] hash = instance.digest(keychain.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; ++i) {
                if ((255 & hash[i]) < 16) {
                    hexString.append("0" + Integer.toHexString(255 & hash[i]));
                } else {
                    hexString.append(Integer.toHexString(255 & hash[i]));
                }
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException var5) {
            throw new InternalErrorException(ErrorCode.INTERNAL_ERROR, "Failed to generate hash digest", new Object[]{var5});
        }
    }
}
