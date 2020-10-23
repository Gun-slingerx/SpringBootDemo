package org.example.util;

import org.apache.commons.codec.binary.Base64;

/**
 * @author yongSen.wang
 * @date 2020/7/14 15:37
 */
public class Base64Util {

    public static String byteToStringBase64(byte[] b) {
        String base64Data = "";
        try {
            base64Data = Base64.encodeBase64String(b);
            if ((base64Data != null) && (base64Data != "")) {
                base64Data = base64Data.replaceAll("\\+", "*").replaceAll("\\/", "-");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return base64Data;
    }

    public static byte[] base64StringToByte(String base64String) {
        if (base64String == null) {
            return null;
        }
        base64String = base64String.replaceAll("\\*", "+").replaceAll("-", "/");

        byte[] b = null;
        b = Base64.decodeBase64(base64String);
        for (int i = 0; i < b.length; i++) {
            if (b[i] < 0) {
                int tmp42_41 = i;
                byte[] tmp42_40 = b;
                tmp42_40[tmp42_41] = ((byte) (tmp42_40[tmp42_41] + 256));
            }
        }
        return b;
    }

    public static void main(String[] args) {
        String s = "调整异常数据";
        System.err.println(byteToStringBase64(s.getBytes()));
    }
}
