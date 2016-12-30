package com.example.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpDownloader {
	private URL url = null;

	public HttpDownloader() {
		
	}

	public String download(String urlStr)
	{
		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader buffer = null;
		
		try {
			url = new URL(urlStr);
			HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
			
			buffer = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			
			while((line = buffer.readLine()) != null)
			{
				sb.append(line);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		} finally { 
			try {
				buffer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return sb.toString();
	}
	
	public int downFile(String urlStr, String path, String fileName){
		InputStream inputStream = null;
		FileUtils fileUtils = new FileUtils();
		try{
			if (fileUtils.isFileExist(path + fileName)) {
				return 1;
			} else {
				inputStream = getInputStreamFromUrl(urlStr);
				File resultFile = fileUtils.write2SDFromInput(path, fileName, inputStream);
				if (resultFile == null)
				{
					return -1;
				}
			}
		}
		catch(Exception e)
		{
			return -1;
		}
		finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		return 0;
	}

	private InputStream getInputStreamFromUrl(String urlStr) {
		// TODO Auto-generated method stub
		InputStream inputStream = null;
		try {
			url = new URL(urlStr);
			HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
			inputStream = urlConn.getInputStream();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return inputStream;
	}
}

