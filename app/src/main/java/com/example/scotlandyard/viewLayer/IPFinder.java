package com.example.scotlandyard.viewLayer;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static android.content.Context.WIFI_SERVICE;

public class IPFinder {
    private  WifiManager wm;
    private String ip;

    public IPFinder(Context context){
        wm = (WifiManager) context.getApplicationContext().getSystemService(WIFI_SERVICE);
    }

    public void findIP(){
        WifiInfo wifiInfo;
        int ipInt;

        if (wm != null) {
            wifiInfo = wm.getConnectionInfo();
            ipInt = wifiInfo.getIpAddress();
        }else {
            throw new NullPointerException();
        }
        try {
            ip = InetAddress.getByAddress(
                    ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(ipInt).array())
                    .getHostAddress();
        } catch (UnknownHostException e) {
            Log.d("IPFinder","Something went wrong while trying to determine the IPv4 address",e);
        }
    }

    public String getIp(){
        return ip;
    }

}
