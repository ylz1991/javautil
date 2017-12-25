package util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpHelp {
    public static String getIpAddress() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        return address.getHostAddress();
    }
}
