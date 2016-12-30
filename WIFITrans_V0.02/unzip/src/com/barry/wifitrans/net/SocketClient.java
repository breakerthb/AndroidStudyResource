package com.barry.wifitrans.net

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.Socket
import java.net.UnknownHostException

import android.util.Log

import com.barry.wifitrans.util.INI

public class SocketClient 

public String askName(String ip) 
String name = ""
Socket socket
try 
socket = new Socket(ip, INI.PORT_SOCKET_CHAT)

// Input & Output strea
InputStream is = socket.getInputStream()
OutputStream os = socket.getOutputStream()

DataInputStream input = new DataInputStream(is)
DataOutputStream output = new DataOutputStream(os)

// Send Data Strea
//Log.d("debug", "Client : Start Send")
output.writeUTF("name")
output.flush()

// Receive Data Strea
name = input.readUTF()
//Log.d("debug", "Client : Receive " + receive)

socket.close()
} catch (UnknownHostException e) 
// TODO Auto-generated catch bloc
e.printStackTrace()
} catch (IOException e) 
// TODO Auto-generated catch bloc
e.printStackTrace()


return name


public void ReceiveFile(String ip) 
final String FILE_PATH = "/mnt/sdcard/"
String sendMessage = "Linux"

try 
Socket socket = new Socket(ip, INI.PORT_SOCKET_FILE_TRAN)

// Send message to Lin
DataOutputStream out = new DataOutputStream(socket.getOutputStream())
out.writeByte(0x1)
out.flush()

// Get messag
DataInputStream inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()))
String savePath = FILE_PATH
int bufferSize = 8192
byte[] buf = new byte[bufferSize]
int passedlen = 0
long len = 0

savePath += inputStream.readUTF()
Log.d("debug","SavePath : " + savePath)
DataOutputStream fileOut = new DataOutputStream
new BufferedOutputStream(new BufferedOutputStream
new FileOutputStream(savePath))))
len = inputStream.readLong()

Log.d("debug","文件的长度为:"+len)
Log.d("debug","开始接收文件")

while(true) 
int read = 0
if (inputStream != null) 
read = inputStream.read(buf)

passedlen += read
if (read == -1) 
break

Log.d("AndroidClient","文件接收了"+(passedlen*100/len)+"%/n")
fileOut.write(buf,0,read)

Log.d("AndroidClient","@@@文件接收完成"+savePath)
fileOut.close()
} catch (UnknownHostException e1) 
// TODO Auto-generated catch bloc
e1.printStackTrace()
} catch (IOException e1) 
// TODO Auto-generated catch bloc
e1.printStackTrace()



public boolean prepareSendFile(String ip, String fileName) 
// TODO Auto-generated method stu
boolean ret = false

Socket socket
try 
socket = new Socket(ip, INI.PORT_SOCKET_FILE)

// Input & Output strea
InputStream is = socket.getInputStream()
OutputStream os = socket.getOutputStream()

DataInputStream input = new DataInputStream(is)
DataOutputStream output = new DataOutputStream(os)

// Send Data Strea
//Log.d("debug", "Client : Start Send")
output.writeUTF(fileName)
output.flush()

// Receive Data Strea
String r = input.readUTF()
//Log.d("debug", "Client : Receive " + receive)
if (r.equals("yes")) 
ret = true


socket.close()
} catch (UnknownHostException e) 
// TODO Auto-generated catch bloc
e.printStackTrace()
} catch (IOException e) 
// TODO Auto-generated catch bloc
e.printStackTrace()


return ret




