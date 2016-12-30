package com.notebook.model;

import android.text.format.Time;

public class Note {

	private int id;
	private String title;
	private String content;
	private String time;
	
	public Note() {
		title = "";
		content = "";
		time = "";
	}
	public Note(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	
	private String GetCurrentTime() {
		 Time time = new Time("GMT+8");    
	     time.setToNow();
	     
	     return time.format("%Y%m%dT%H%M%S");
	}
	
	public int getId() {
		return id;
	}
	
	public String getTime() {
		return time;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public void setTime(String time) {
		this.time = time;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
