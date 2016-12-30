package com.barry.projectzip.filter;

import com.barry.projectzip.Tag;

public class RecoverLine implements Filter {

	@Override
	public String doFilt(String str) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		int find = 0;
		int start = 0;
		while ((find = str.indexOf(Tag.LINE, start)) != -1) {
			if (find > start) {
				sb.append(str.substring(start, find - 1));
			}
			sb.append("\n");
			start = find + 2;
		}
		
		sb.append(str.substring(start));
		sb.append("\n");
		return sb.toString();
	}

}
