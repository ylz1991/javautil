package util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

public abstract class ArrayHelper {
    private transient static final String DEFAULT_JOIN_STR = ",";

    public static boolean IsNullOrEmptyString(String[] arrayString) {
        if (arrayString == null) return true;
        if (arrayString.length == 0) return true;
        return false;
    }

    /**
     * 连接字符串 如：[1,2,3,4]，将会返回"1,2,3,4"
     */
    public static final String join(final Object[] arr) {
        return join(arr, DEFAULT_JOIN_STR);
    }

    /**
     * 连接字符串 如: join([1,2,3,4],"x"]) 将会返回"1x2x3x4"
     */
    public static final String join(final Object[] arr, final String joinStr) {
        if (arr == null || arr.length < 1) {
            return StringUtils.EMPTY;
        }
        final StringBuffer sb = new StringBuffer(String.valueOf(arr[0]));
        for (int i = 1, len = arr.length; i < len; i++) {
            sb.append(StringUtils.isNotEmpty(joinStr) ? joinStr : StringUtils.EMPTY).append(String.valueOf(arr[i]));
        }
        return sb.toString();
    }

    /**
     * 集合转数组
     */
    @SuppressWarnings("unchecked")
    public static final <T> T[] toArray(final Collection<T> collection, final Class<T> clazz) {
        if (collection == null) {
            return null;
        }
        final T[] arr = (T[]) Array.newInstance(clazz, collection.size());
        return collection.toArray(arr);
    }

    /**
     * 集合转对象数组
     */
    public static final Object[] toObjectArray(final Collection<?> collection) {
        if (collection == null) {
            return null;
        }
        final Object[] arr = (Object[]) Array.newInstance(Object.class, collection.size());
        int i = 0;
        for (Iterator<?> it = collection.iterator(); it.hasNext(); ) {
            arr[i++] = it.next();
        }
        return arr;
    }
}
