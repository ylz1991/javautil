package util;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class RandomHelper {
    /**
     * 32位字符串
     */
    public static String getRandomCodeIs32Byte() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    /**
     * 24位唯一字符串
     */
    public static String getUniqueCodeIs24Byte() {
        String str = "";
        try {
            String s = UUID.randomUUID().toString();
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] bs = md.digest(s.getBytes());
            BASE64Encoder base = new BASE64Encoder();
            str = base.encode(bs);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 10位随机数字
     */
    public static String getRandomCodeIs10Byte() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            stringBuffer.append((int) (10 * (Math.random())));
        }
        return stringBuffer.toString();
    }
}
