package com.barry.wifitrans.util;

import java.util.ArrayList;
import java.util.List;

import com.barry.wifitrans.model.Member;
import com.barry.wifitrans.model.UserInfo;

import android.app.Application;

public class MyApplication extends Application {

	private UserInfo myInfo = null;
	
	private String toIp = "";

	public List<Member> listMembers = null;


	public UserInfo getMyInfo() {
		return myInfo;
	}
	
	public String getToIp() {
		return toIp;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		myInfo = new UserInfo();
		
		listMembers = new ArrayList<Member>();
		
		super.onCreate();
	}


	public void setMyInfo(UserInfo myInfo) {
		this.myInfo = myInfo;
	}


	public void setToIp(String toIp) {
		this.toIp = toIp;
	}

}
