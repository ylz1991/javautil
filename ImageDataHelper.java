package util;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 图片与图片编码互转
 */
public class ImageDataHelper {
    /**
     * base64编码字符串
     */
    public static boolean generateImage(String imgStr, String path) {
        if (imgStr == null)
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 远程读取image转换为Base64字符串
     */
    public static String image2Base64(String imgUrl) {
        URL url = null;
        InputStream is = null;
        ByteArrayOutputStream outStream = null;
        HttpURLConnection httpUrl = null;
        try {
            url = new URL(imgUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            httpUrl.getInputStream();
            is = httpUrl.getInputStream();
            outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            return Base64.encodeBase64String(outStream.toByteArray());
        } catch (Exception e) {
            return "";
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    return "";
                }
            }
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    return "";
                }
            }
            if (httpUrl != null) {
                httpUrl.disconnect();
            }
        }
    }

    /**
     * 图片转化成base64字符串
     */
    public static String GetImageStr(String imgFile) throws Exception {
        File file = new File(imgFile);
        if (file.exists()) {
            InputStream in = null;
            byte[] data = null;
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(data);
        }
        return null;
    }

    /**
     * 图片转化成base64字符串
     */
    public static String GetImageByStream(InputStream in) throws Exception {
        byte[] data = null;
        data = new byte[in.available()];
        in.read(data);
        in.close();
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    /**
     * 获取file文件图片宽高
     */
    public static int[] getImgWidth(File file) {
        int[] imgSize = {};
        try {
            InputStream is = new FileInputStream(file);
            BufferedImage src = javax.imageio.ImageIO.read(is);
            int width = src.getWidth(null);
            int height = src.getHeight(null);
            is.close();
            imgSize[0] = width;
            imgSize[1] = height;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgSize;
    }
}