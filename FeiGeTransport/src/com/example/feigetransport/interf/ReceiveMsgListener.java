package com.example.feigetransport.interf;

import com.example.feigetransport.model.ChatMessage;

/**
 * ������Ϣ������listener�ӿ�
 * @author ccf
 *
 */
public interface ReceiveMsgListener {
	public boolean receive(ChatMessage msg);

}
