package util;

import java.util.Date;

public class LockEntity {
    public LockEntity(String KeyId) {
        setKeyId(KeyId);
        setLockTime(new Date());
        setMinute(1);
    }

    public String getKeyId() {
        return KeyId;
    }

    public void setKeyId(String keyId) {
        KeyId = keyId;
    }

    private String KeyId;

    public Date getLockTime() {
        return LockTime;
    }

    public void setLockTime(Date lockTime) {
        LockTime = lockTime;
    }

    /*
    * 锁定业务时间
    * */
    private Date LockTime;

    public int getMinute() {
        return Minute;
    }

    public void setMinute(int minute) {
        Minute = minute;
    }

    /*
        * 锁定时长分钟
        * */
    private int Minute;

    /*
    * 获取过期状态
    * */
    public boolean getOverDueStatus() {
        Date date = new Date();
        long m = (date.getTime() - LockTime.getTime()) / (1000 * 60);
        if ((m - Minute) > 0) {
            return true;
        } else {
            return false;
        }
    }
}
