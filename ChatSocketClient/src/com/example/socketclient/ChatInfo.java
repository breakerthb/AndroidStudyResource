package com.example.socketclient;

import java.util.Date;

public class ChatInfo {
	private Date time;
	private String msg;
	private boolean isComeMsg;
	
	public String getMsg() {
		return msg;
	}
	public Date getTime() {
		return time;
	}
	
	public String getStrTime(){
		@SuppressWarnings("deprecation")
		String str = time.getYear() + "-" + time.getMonth() + "-" + time.getDay() + " " +
					 time.getHours() + ":" + time.getMinutes() + ":" + time.getSeconds();
		
		return str;
	}
	
	public boolean isComeMsg() {
		return isComeMsg;
	}
	
	public void setComeMsg(boolean isComeMsg) {
		this.isComeMsg = isComeMsg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
