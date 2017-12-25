package util;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public abstract class StringHelper {
    public static boolean isEmpty(String value) {
        int strLen;
        if (value == null || (strLen = value.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(value.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNumeric(Object obj) {
        if (obj == null) {
            return false;
        }
        char[] chars = obj.toString().toCharArray();
        int length = chars.length;
        if (length < 1)
            return false;
        int i = 0;
        if (length > 1 && chars[0] == '-')
            i = 1;
        for (; i < length; i++) {
            if (!Character.isDigit(chars[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean areNotEmpty(String... values) {
        boolean result = true;
        if (values == null || values.length == 0) {
            result = false;
        } else {
            for (String value : values) {
                result &= !isEmpty(value);
            }
        }
        return result;
    }

    public static String changeCharset(String str, String newCharset)
            throws UnsupportedEncodingException {
        if (str != null) {
            //用默认字符编码解码字符串。
            byte[] bs = str.getBytes();
            //用新的字符编码生成字符串
            return new String(bs, newCharset);
        }
        return null;
    }

    public static boolean StringIsNullOrEmpty(String str) {
        if (str == null) return true;
        if (str.isEmpty()) return true;
        return false;
    }

    public static boolean IsNullOrEmpty(String str) {
        if (str == null) return true;
        if (str.trim().isEmpty()) return true;
        return false;
    }

    public static boolean SearchIsContain(String searchStr, List<String> list) {
        if (list == null) return false;
        for (String str : list) {
            if (searchStr.indexOf(str) > -1) {
                return true;
            }
        }
        return false;
    }

    public static void RemoveEmpty(List<String> list) {
        boolean result = true;
        while (result) {
            result = list.remove("");
        }
    }

    /*
    * 一个字符如果长度不够，在左边补上相应个字符
    * */
    public static String PadLeft(String str, int length, char c) {
        if (length <= 0)
            return str;
        int strLength = 0;
        if (!IsNullOrEmpty(str)) {
            strLength = str.length();
        }
        if (strLength >= length)
            return str;
        while (str.length() < length)
            str = c + str;
        return str;
    }

    /*
    * 字符串必须全部是数字
    * */
    public static boolean IsNum(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static boolean IsLetterORNumber(String str) {
        Pattern pattern = Pattern.compile("^(([a-z]|\\d)*)$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static String InterceptStr(String str, int length) {
        if (str == null)
            return "";
        if (str.length() <= length)
            return str;
        return str.substring(0, length);
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static String subString(String message, int length) {
        if (StringIsNullOrEmpty(message))
            return "";
        if (message.length() > length)
            return message.substring(0, length);
        return message;
    }

    public static String filterEmoji(String source) {
        if (source != null) {
            Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
            Matcher emojiMatcher = emoji.matcher(source);
            if (emojiMatcher.find()) {
                source = emojiMatcher.replaceAll("*");
                return source;
            }
            return source;
        }
        return source;
    }

    public static String StringFilter(String str) throws PatternSyntaxException {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
} 
