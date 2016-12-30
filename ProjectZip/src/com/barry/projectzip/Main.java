package com.barry.projectzip;
import java.io.File;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = null;
		if (args.length == 1) {
			path = new File("").getAbsolutePath();
		} else if (args.length == 2) {
			path = args[1].toString();
			path = path.replace("/", "\\");
		} else {
			System.out.println("Parameters Error!");
			return;
		}
		
		String para = args[0].toString();
		
		if (para.equals("zip")){
			FilesZip filesZip = new FilesZip(path);
			filesZip.process();
			
		} else if (para.equals("unzip")) {
			FilesUnzip filesUnzip = new FilesUnzip(path);
			filesUnzip.process();
		} else {
			return;
		}
		
		System.out.println("complete");
	}

}
