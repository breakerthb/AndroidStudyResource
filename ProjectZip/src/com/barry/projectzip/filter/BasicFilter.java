package com.barry.projectzip.filter;

import com.barry.projectzip.Tag;

public class BasicFilter implements Filter {

	@Override
	public String doFilt(String str) {
		// TODO Auto-generated method stub
		
		String strT = str;
		
		while (true) {
			int find = strT.indexOf("  ");
			if (find == -1) {
				break;
			}
			
			strT = strT.replace("  ", " ");
		}
		
		strT = strT.replace("\t", "");
		strT = strT.replace(" " + Tag.LINE, Tag.LINE);
		strT = strT.replace(Tag.LINE + " ", Tag.LINE);
		return strT;
	}

}
