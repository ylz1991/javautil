package util;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ContentTypeHelp {
    /**
     * 获取文件Content-Type(Mime-Type)
     */
    public static String fileContentType(String filePath) {
        String contentType = "";
        if (new File(filePath).exists()) {
            Path path = Paths.get(filePath);
            try {
                contentType = Files.probeContentType(path);
            } catch (IOException e) {
            }
        }
        return contentType;
    }

    /**
     * 获取文件Content-Type(Mime-Type)
     */
    public static String fileContentType(File file) {
        String contentType = "";
        if (file.exists()) {
            Path path = Paths.get(file.getAbsolutePath());
            try {
                contentType = Files.probeContentType(path);
            } catch (IOException e) {
            }
        }
        return contentType;
    }

    /**
     * 获取文件Content-Type(Mime-Type)
     */
    public static String fileContentTypeUrl(String url) {
        String contentType = "";
        try {
            FileNameMap fileNameMap = URLConnection.getFileNameMap();
            contentType = fileNameMap.getContentTypeFor(url);
        } catch (Exception e) {
        }
        return contentType;
    }
}
