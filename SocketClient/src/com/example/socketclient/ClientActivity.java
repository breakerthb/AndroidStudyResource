package com.example.socketclient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ClientActivity extends Activity {

	public static String SERVER_IP = "10.0.2.2";
	public static int SERVER_PORT = 2888;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_client);
		
		Button btnSend = (Button) findViewById(R.id.btnSend);
		btnSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						//TCPMethod();
						//UDPMethod();
						FileTran();
					}
				}.start();
			}
		});
	}

	private void TCPMethod()
	{
		try {
			// Bound server IP & Port
			Socket socket = new Socket(SERVER_IP, SERVER_PORT);
			
			// Input & Output stream
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
			DataInputStream input = new DataInputStream(is);
			DataOutputStream output = new DataOutputStream(os);
			
			// Send Data Stream
			Log.d("debug", "Client : Start Send");
			output.writeUTF("Test");
			output.flush();

			// Receive Data Stream
			String receive = input.readUTF();
			Log.d("debug", "Client : Receive " + receive);
			
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void UDPMethod()
	{
		DatagramSocket socket;
		try {
			socket = new DatagramSocket(2888);
			InetAddress serverAddress = InetAddress.getByName(SERVER_IP); 
			String str = "hello";

			byte data[] = str.getBytes();

			DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, SERVER_PORT);

			socket.send(packet);
			
			socket.close();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	private void FileTran(){
		final String FILE_PATH = "/mnt/sdcard/";
		String sendMessage = "Linux";
		
		try {
			Socket socket = new Socket(SERVER_IP, SERVER_PORT);
			
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
			
			Log.d("debug","文件的长度为:"+len);
			Log.d("debug","开始接收文件");
			
			while(true) {
				int read = 0;
				if (inputStream != null) {
					read = inputStream.read(buf);
				}
				passedlen += read;
				if (read == -1) {
					break;
				}
				Log.d("AndroidClient","文件接收了"+(passedlen*100/len)+"%/n");
				fileOut.write(buf,0,read);
			}
			Log.d("AndroidClient","@@@文件接收完成"+savePath);
			fileOut.close();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.client, menu);
		return true;
	}

}
