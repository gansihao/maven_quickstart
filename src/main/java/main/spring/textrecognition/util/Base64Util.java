package main.spring.textrecognition.util;

import java.util.Base64;

public class Base64Util {

    public static String byte2Base64(byte[] bytes) {
        Base64.Encoder encoder = Base64.getEncoder();
        String base64 = encoder.encodeToString(bytes);
        return base64;
    }
}
