package com.barry.wifitrans.net;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.util.Log;

import com.barry.wifitrans.util.INI;

public class SocketClient {
	
	public String askName(String ip) {
		String name = "";
		Socket socket;
		try {
			socket = new Socket(ip, INI.PORT_SOCKET_CHAT);
			
			// Input & Output stream
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
			DataInputStream input = new DataInputStream(is);
			DataOutputStream output = new DataOutputStream(os);
			
			// Send Data Stream
			//Log.d("debug", "Client : Start Send");
			output.writeUTF("name");
			output.flush();

			// Receive Data Stream
			name = input.readUTF();
			//Log.d("debug", "Client : Receive " + receive);
			
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return name;
	}
	
	public void ReceiveFile(String ip) {
		final String FILE_PATH = "/mnt/sdcard/";
		String sendMessage = "Linux";
		
		try {
			Socket socket = new Socket(ip, INI.PORT_SOCKET_FILE_TRAN);
			
			// Send message to Link
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeByte(0x1);
			out.flush();
			
			// Get message
			DataInputStream inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			String savePath = FILE_PATH;
			int bufferSize = 8192;
			byte[] buf = new byte[bufferSize];
			int passedlen = 0;
			long len = 0;
			
			savePath += inputStream.readUTF();
			Log.d("debug","SavePath : " + savePath);
			DataOutputStream fileOut = new DataOutputStream(
					new BufferedOutputStream(new BufferedOutputStream(
							new FileOutputStream(savePath))));
			len = inputStream.readLong();
			
			Log.d("debug","�ļ��ĳ���Ϊ:"+len);
			Log.d("debug","��ʼ�����ļ�");
			
			while(true) {
				int read = 0;
				if (inputStream != null) {
					read = inputStream.read(buf);
				}
				passedlen += read;
				if (read == -1) {
					break;
				}
				Log.d("AndroidClient","�ļ�������"+(passedlen*100/len)+"%/n");
				fileOut.write(buf,0,read);
			}
			Log.d("AndroidClient","@@@�ļ��������"+savePath);
			fileOut.close();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public boolean prepareSendFile(String ip, String fileName) {
		// TODO Auto-generated method stub
		boolean ret = false;
		
		Socket socket;
		try {
			socket = new Socket(ip, INI.PORT_SOCKET_FILE);
			
			// Input & Output stream
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
			DataInputStream input = new DataInputStream(is);
			DataOutputStream output = new DataOutputStream(os);
			
			// Send Data Stream
			//Log.d("debug", "Client : Start Send");
			output.writeUTF(fileName);
			output.flush();

			// Receive Data Stream
			String r = input.readUTF();
			//Log.d("debug", "Client : Receive " + receive);
			if (r.equals("yes")) {
				ret = true;
			}
			
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}
}