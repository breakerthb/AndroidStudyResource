package com.barry.projectzip;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperate {
	private FileReader fr = null;
	private BufferedReader br = null;
	
	private FileWriter fw = null;
	private BufferedWriter bw = null;
	
	private int flag = 0;
	
	private String fileText = null;
	
	public static int FOR_READ = 1;
	public static int FOR_WRITE = 2;
	
	
	public boolean load(String path, int flag) {
		if (path.trim().isEmpty()) {
			System.out.println("The path is empty...");
			return false;
		}
		
		this.flag = flag;
		
		File file = new File(path);
		boolean exists = file.exists();
		
		try {
			if (flag == FOR_READ) {
				if (!exists) {
					System.out.println("File not Find : " + path);
					return false;
				}
				
				fr = new FileReader(path);
				br = new BufferedReader(fr);
			} else if (flag == FOR_WRITE) {
				if (!exists) {
					String newpath = file.getParent();
					if (new File(newpath).mkdirs()) {
						System.out.println("File not Find : " + path + ", but create success!");
					} else {
						System.out.println("File not Find : " + path);
					}
					
				}
				
				fw = new FileWriter(path);
				bw = new BufferedWriter(fw);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean haveLoad() {
		if (fr == null && fw == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public String readLine(){
		if (flag == FOR_READ) {
			try {
				return br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public boolean writeLine(String line){
		if (flag == FOR_WRITE) {
			try {
				bw.write(line);
				bw.write("\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return true;
	}
	
	public boolean release() {
		
		try {
			if (br != null) {
				br.close();
			}
			if (fr != null) {
				fr.close();
			}

			if (bw != null) {
				bw.close();
			}
			if (fw != null) {
				fw.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
}