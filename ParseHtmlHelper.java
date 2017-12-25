package util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ParseHtmlHelper {
    private static final String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) " +
            "Chrome/38.0.2125.122 Safari/537.36 SE 2.X MetaSr 1.0";

    public static Document parseHtml(String url, String referrer) {
        try {
            Connection conn = Jsoup.connect(url).userAgent(userAgent);
            if (referrer != null && !referrer.trim().isEmpty()) {
                conn.referrer(referrer);
            }
            Document doc = conn.get();
            return Jsoup.parse(doc.getElementsByTag("html").outerHtml());
        } catch (IOException e) {
            throw new RuntimeException("parsing html io exception");
        }
    }
} 
