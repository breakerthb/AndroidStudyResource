package com.barry.wifitrans.net

import com.barry.wifitrans.util.INI

import android.content.Context
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager

public class WIFI 

Context context = null
WifiManager wifiManager = null

public WIFI(Context context) 
this.context = context
wifiManager = (WifiManager) contex
.getSystemService(Context.WIFI_SERVICE)



public void CheckWIFI() 
if (!wifiManager.isWifiEnabled()) 
wifiManager.setWifiEnabled(true)



public String GetMyIP() 
WifiInfo wifiInfo = wifiManager.getConnectionInfo()
int ipAddress = wifiInfo.getIpAddress()
String ip = intToIp(ipAddress)


return ip


private String intToIp(int i) 
return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF
+ "." + (i >> 24 & 0xFF)





