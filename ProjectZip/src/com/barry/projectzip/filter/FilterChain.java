package com.barry.projectzip.filter;

import java.util.ArrayList;
import java.util.List;

public class FilterChain {
	List<Filter> chain = null;
	
	public FilterChain() {
		chain = new ArrayList<Filter>();
	}
	
	public void addFilter(Filter filter) {
		chain.add(filter);
	}
	
	public String doFilterChain(String str) {
		String strRet = str;
		
		for (int i = 0; i < chain.size(); i++) {
			strRet = chain.get(i).doFilt(strRet);
		}
		
		return strRet;
	}
}
