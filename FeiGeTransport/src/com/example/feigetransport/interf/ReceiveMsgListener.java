package com.example.feigetransport.interf;

import com.example.feigetransport.model.ChatMessage;

/**
 * 接收消息监听的listener接口
 * @author ccf
 *
 */
public interface ReceiveMsgListener {
	public boolean receive(ChatMessage msg);

}
