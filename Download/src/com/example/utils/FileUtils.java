package com.example.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Environment;

public class FileUtils {

	private String SDPATH = "";

	public FileUtils() {
		SDPATH = Environment.getExternalStorageDirectory() + "/";
	}

	public String getSDPATH() {
		return SDPATH;
	}
	
	public void setSDPATH(String sDPATH) {
		SDPATH = sDPATH;
	}

	public File createSDFile(String fileName)
	{
		File file = new File(SDPATH + fileName);
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}
	
	public File createSDDir(String dirName)
	{
		File dir = new File(SDPATH + dirName);
		boolean ret = dir.mkdir();
		return dir;	
	}
	
	public boolean isFileExist(String fileName)
	{
		File file = new File(SDPATH + fileName);
		return file.exists();
	}
	
	public File write2SDFromInput(String path, String fileName, InputStream input)
	{
		File file = null;
		OutputStream output = null;
		
		createSDDir(path);
		File f = createSDFile(path + fileName);
		try {
			output = new FileOutputStream(f);
			byte buffer[] = new byte[4 * 1024];
			while((input.read(buffer)) != -1)
			{
				output.write(buffer);
			}
			
			System.out.println(path);
			file = new File(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return file;
	}
}
