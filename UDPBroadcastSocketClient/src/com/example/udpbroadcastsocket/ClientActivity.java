package com.example.udpbroadcastsocket;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ClientActivity extends Activity {

	/*���͹㲥�˵�socket*/
	private MulticastSocket ms;
	/*���͹㲥�İ�ť*/
	private Button sendUDPBrocast;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sendUDPBrocast = (Button) findViewById(R.id.btnSend);
		sendUDPBrocast.setOnClickListener(new SendUDPBrocastListener());
		try {
			/*����socketʵ��*/
			ms = new MulticastSocket();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * ������ťʱ�����;������㲥
	 * */
	class SendUDPBrocastListener implements OnClickListener{
		
		@Override
		public void onClick(View v) {
			//���͵����ݰ��������ڵ����е�ַ�������յ������ݰ�
			DatagramPacket dataPacket = null;
			
			try {
				ms.setTimeToLive(4);
				
				//��������IP���������д��̬��ȡ��IP����ַ�ŵ����ݰ����ʵserver�˽��յ����ݰ���Ҳ�ܻ�ȡ����������IP��
				byte[] data = "192.168.19.1".getBytes();
				
				//224.0.0.1Ϊ�㲥��ַ
				InetAddress address = InetAddress.getByName("224.0.0.1");
				//����ط���������жϸõ�ַ�ǲ��ǹ㲥���͵ĵ�ַ
				System.out.println(address.isMulticastAddress());
				dataPacket = new DatagramPacket(data, data.length, address,	8003);
				ms.send(dataPacket);
				ms.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

}
