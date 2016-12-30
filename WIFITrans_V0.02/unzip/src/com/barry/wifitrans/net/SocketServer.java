package com.barry.wifitrans.net

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.ServerSocket
import java.net.Socket
import java.net.UnknownHostException

import com.barry.wifitrans.util.INI

import android.os.Handler
import android.util.Log

public class SocketServer 

private ServerSocket serverSocket = null

public SocketServer(int listenPort) 
try 
serverSocket = new ServerSocket(listenPort)
} catch (IOException e) 
// TODO Auto-generated catch bloc
e.printStackTrace()



public void close() 
try 
serverSocket.close()
} catch (IOException e) 
// TODO Auto-generated catch bloc
e.printStackTrace()



public void beAskListen() 
try 
// Get client acces
Socket socket = serverSocket.accept()

// Input & Output strea
InputStream is = socket.getInputStream()
OutputStream os = socket.getOutputStream()

DataInputStream input = new DataInputStream(is)
DataOutputStream output = new DataOutputStream(os)

// Receive info from clien
String strReceive = input.readUTF()
// Log.d("debug", "Server : Receive " + strReceive)
String send = ""
if (strReceive.equals("name")) 
send = android.os.Build.MODEL
} else 
send = "sorry"


// Send info to clien
output.writeUTF(send)
output.flush()

socket.close()
} catch (IOException e) 
// TODO Auto-generated catch bloc
e.printStackTrace()




public void SendFile(final String filePath) 
new Thread() 

@Overrid
public void run() 
// TODO Auto-generated method stu
try 
File file = new File(filePath)

Log.d("debug", "Server Listen...")
Socket socket = serverSocket.accept()

DataInputStream dis = new DataInputStream
new BufferedInputStream(socket.getInputStream()))
dis.readByte()

DataInputStream fis = new DataInputStream
new BufferedInputStream(new FileInputStream
filePath)))
DataOutputStream dos = new DataOutputStream
socket.getOutputStream())

dos.writeUTF(file.getName())
dos.flush()
dos.writeLong((long) file.length())
dos.flush()

int bufferSize = 8192
byte[] buf = new byte[bufferSize]
while (true) 
int read = 0
if (fis != null) 
read = fis.read(buf)


if (read == -1) 
break

dos.write(buf, 0, read)

dos.flush()

// 注意关闭socket链接哦，不然客户端会等待server的数据过来
// 直到socket超时，导致数据不完整
fis.close()
socket.close()
} catch (IOException e) 
// TODO Auto-generated catch bloc
e.printStackTrace()



}.start()



public String prepareReceiveFile() 
// TODO Auto-generated method stu
// Get client acces
String targIP = ""
try 
Socket socket = serverSocket.accept()

// Input & Output strea
InputStream is = socket.getInputStream()
OutputStream os = socket.getOutputStream()

DataInputStream input = new DataInputStream(is)
DataOutputStream output = new DataOutputStream(os)

// Receive info from clien
String fileName = input.readUTF()
// Log.d("debug", "Server : Receive " + strReceive)
String send = "yes"

// Send info to clien
output.writeUTF(send)
output.flush()

// 对方地
targIP = socket.getInetAddress().getHostAddress()

socket.close()


} catch (IOException e) 
// TODO Auto-generated catch bloc
e.printStackTrace()


return targIP





