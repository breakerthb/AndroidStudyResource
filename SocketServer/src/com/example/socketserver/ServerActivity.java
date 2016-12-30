package com.example.socketserver;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.URI;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ServerActivity extends Activity {

	private static int HOST_PORT = 2888;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_server);
		
		Button btnListen = (Button) findViewById(R.id.btnListen);
		btnListen.setOnClickListener(new OnClickListener() {
			
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
			ServerSocket serverSocket = new ServerSocket(HOST_PORT);
			
			// Get client access
			Log.d("debug", "Server : Start Listen...");
			Socket socket = serverSocket.accept();
			
			// Input & Output stream
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
			DataInputStream input = new DataInputStream(is);
			DataOutputStream output = new DataOutputStream(os);
			
			// Receive info from client
			String strReceive = input.readUTF();
			Log.d("debug", "Server : Receive " + strReceive);
			
			// Send info to client
			output.writeUTF("You Say : " + strReceive);
			output.flush();
			
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("debug", "Server : " + e.getMessage());
		}
	}
	
	private void UDPMethod(){
		try {
			DatagramSocket socket = new DatagramSocket (HOST_PORT);
			byte data[] = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);

			Log.d("debug", "Server : Start Listen...");
			socket.receive(packet); 

			String strReceive = new String(packet.getData(), packet.getOffset() , packet.getLength());
			
			Log.d("debug", "Server : Receive " + strReceive);
			
			socket.close();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	private void FileTran(){
	        try {  
	            ServerSocket serverSocket = new ServerSocket(HOST_PORT);  
	            
                String filePath = "/mnt/sdcard/play.png";  
                File file = new File(filePath);  
                
                Log.d("debug", "Server Listen...");  
                Socket socket = serverSocket.accept();  
                
                DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));  
                dis.readByte();  
                
                DataInputStream fis = new DataInputStream(new BufferedInputStream(new FileInputStream(filePath)));  
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());  
                
                dos.writeUTF(file.getName());  
                dos.flush();  
                dos.writeLong((long) file.length());  
                dos.flush();  
                
                int bufferSize = 8192;  
                byte[] buf = new byte[bufferSize];  
                while (true) {  
                    int read = 0;  
                    if (fis != null) {  
                        read = fis.read(buf);  
                    }  
                    
                    if (read == -1) {  
                        break;  
                    }  
                    dos.write(buf, 0, read);  
                }  
                dos.flush();  
                
                 // 注意关闭socket链接哦，不然客户端会等待server的数据过来，   
                // 直到socket超时，导致数据不完整。   
                fis.close();  
                socket.close();  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block   
	            e.printStackTrace();  
	        }  

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.server, menu);
		return true;
	}

}
