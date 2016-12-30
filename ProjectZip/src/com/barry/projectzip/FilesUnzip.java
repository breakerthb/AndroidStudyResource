package com.barry.projectzip;

import com.barry.projectzip.filter.FilterChain;
import com.barry.projectzip.filter.RecoverLine;

public class FilesUnzip {

	private String rootPath = "";
	private FilterChain filterChain = null;
	
	public FilesUnzip(String path) {
		// TODO Auto-generated constructor stub
		rootPath = path;
		if (rootPath.endsWith("\\")) {
			// Do nothing
		} else if (rootPath.endsWith("/")) {
			rootPath = rootPath.replace("/", "\\");
		} else {
			rootPath += "\\";
		}
		
		rootPath += "unzip\\";
		
		filterChain = new FilterChain();
		filterChain.addFilter(new RecoverLine());
	}

	public void process() {
		// TODO Auto-generated method stub
		FileOperate fin = new FileOperate();
		fin.load("out.txt", FileOperate.FOR_READ);
		
		FileOperate fout = new FileOperate();
		String line = "";
		while ((line = fin.readLine()) != null) {
			// File Header
			int find = line.indexOf(Tag.FILE_NAME_START);
			if (find == -1) {
				// Do nothing
			} else if (find == 0) {
				int start = find + Tag.FILE_NAME_START.length();
				find = line.indexOf(Tag.FILE_NAME_END);
				int end = find;
				String fileName = line.substring(start + 1, end);
				
				String filePath = rootPath + fileName;
				if (fout.haveLoad()) {
					fout.release();
				}
				fout.load(filePath, FileOperate.FOR_WRITE);
				
				continue;
			} else {
				System.out.println("File name error!");
			}
			
			//////////////////
			// File Content
			line = filterChain.doFilterChain(line);
			fout.writeLine(line);
		}
		
		fout.release();
		fin.release();
	}

}
