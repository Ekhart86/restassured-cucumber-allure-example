package utils;

import java.util.Base64;

public class Util {

    public static String convertStringToBase64(String string) {
        return Base64.getEncoder().encodeToString(string.getBytes());
    }
}
