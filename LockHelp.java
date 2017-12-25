package util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class LockHelp {
    private static HashMap<String, LockEntity> lockEntityHashMap = new HashMap<>();
    private static Object lock = new Object();

    public static boolean getIsAvailable(String KeyId) {
        synchronized (lock) {
            if (lockEntityHashMap.containsKey(KeyId)) {
                LockEntity lockEntity = lockEntityHashMap.get(KeyId);
                if (lockEntity.getOverDueStatus()) {
                    lockEntity.setLockTime(new Date());
                    return true;
                } else {
                    return false;
                }
            } else {
                LockEntity lockEntity = new LockEntity(KeyId);
                lockEntityHashMap.put(KeyId, lockEntity);
                return true;
            }
        }
    }

    public static void clearLock(String KeyId) {
        synchronized (lock) {
            lockEntityHashMap.remove(KeyId);
            if (lockEntityHashMap.size() > 1000) {
                List<String> keyIdList = new ArrayList<String>();
                for (String tempKeyId : lockEntityHashMap.keySet()) {
                    if (lockEntityHashMap.get(tempKeyId).getOverDueStatus()) {
                        keyIdList.add(tempKeyId);
                    }
                }
                for (String tempKeyId : keyIdList) {
                    lockEntityHashMap.remove(tempKeyId);
                }
            }
        }
    }

    public static String generalKey(long tenantRowId, long rowId, String typeName) {
        return new StringBuffer().append(tenantRowId).append(":").append(rowId).append(":").append(typeName).toString();
    }

    public static boolean lock(long tenantRowId, long rowId, String typeName) {
        String key = generalKey(tenantRowId, rowId, typeName);
        return getIsAvailable(key);
    }

    public static void unLock(long tenantRowId, long rowId, String typeName) {
        String key = generalKey(tenantRowId, rowId, typeName);
        clearLock(key);
    }

    /**
     * 拿到锁，如果该锁被其他人拿到，则在到达指定的等待时间之前等待可用的锁
     */
    public static boolean lock(long tenantRowId, long rowId, String typeName, long timeout) throws Exception {
        String key = generalKey(tenantRowId, rowId, typeName);
        boolean getLock = false;
        long time = System.currentTimeMillis();
        time = time + timeout * 1000;
        while (!getLock) {
            getLock = getIsAvailable(key);
            if (!getLock) {
                Thread.sleep(100);
            }
            if (time <= System.currentTimeMillis()) break;
        }
        return getLock;
    }
} 
