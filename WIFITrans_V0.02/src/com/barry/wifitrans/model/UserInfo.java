package com.barry.wifitrans.model;

public class UserInfo {
	private String ip = null;
	private String name = null;
	
	public UserInfo() {
		ip = "";
		name = "";
	}
	
	public UserInfo(String ip, String name) {
		this.ip = ip;
		this.name = name;
	}
	
	public String getIp() {
		return ip;
	}
	public String getName() {
		return name;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setName(String name) {
		this.name = name;
	}
}
