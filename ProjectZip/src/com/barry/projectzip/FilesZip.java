package com.barry.projectzip;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.barry.projectzip.filter.BasicFilter;
import com.barry.projectzip.filter.FilterChain;

public class FilesZip {
	private List<String> list = null;
	private String rootPath = null;
	private FilterChain filterChain = null;
	
	public FilesZip(String rootPath) {
		list = new ArrayList<String>();
		
		if (rootPath.endsWith("\\")) {
			// Do nothing
		} else if (rootPath.endsWith("/")) {
			rootPath = rootPath.replace("/", "\\");
		} else {
			rootPath += "\\";
		}
		this.rootPath = rootPath;
		
		
		filterChain = new FilterChain();
		filterChain.addFilter(new BasicFilter());
	}

	public void process() {
		String srcPath = rootPath + "src";
		if (new File(srcPath).exists()) {
			refreshFileList(srcPath);
		} 
		
		// AndroidManifest.xml
		String xml = srcPath.substring(0, srcPath.length() - 3);
		xml += "AndroidManifest.xml";
		if (new File(xml).exists()) {
			list.add(xml);
		}
		
		// res/layout
		String layout = rootPath + "res\\layout\\";
		if (new File(layout).exists()) {
			refreshFileList(layout);
		} 
		
		// res/values
		String values = rootPath + "res\\values\\";
		if (new File(values).exists()) {
			refreshFileList(values);
		} 
		
		if (list.size() == 0) {
			System.out.println("No Files");
			return;
		}
		
		////////////////////////////////////////////////////////////////
		// Write File
		FileOperate out = new FileOperate();
		out.load("out.txt", FileOperate.FOR_WRITE);
		
		System.out.println("Start to write fiel ...");
		
		for (int i = 0; i < list.size(); i++) {
			FileOperate in = new FileOperate();
			String str = list.get(i);
			in.load(str, FileOperate.FOR_READ);
			
			System.out.println(str);
			
			String strFileName = Tag.FILE_NAME_START;
			strFileName += list.get(i).substring(rootPath.length() - 1);
			strFileName += Tag.FILE_NAME_END;
			
			System.out.println(strFileName);
			
			String line = "";
			String text = "";
			while ((line = in.readLine()) != null) {
				text += line + Tag.LINE;
			}
			in.release();
			
			out.writeLine(strFileName);
			out.writeLine(filterChain.doFilterChain(text));
		}
		
		out.release();
		
		
	}

	private void refreshFileList(String strPath) {
		File dir = new File(strPath);
		File[] files = dir.listFiles();

		if (files == null)
			return;
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				refreshFileList(files[i].getAbsolutePath());
			} else {
				String strFileName = files[i].getAbsolutePath().toLowerCase();
				System.out.println("---" + strFileName);
				list.add(files[i].getAbsolutePath());
			}
		}
	}
}