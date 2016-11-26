package com.qixiu.common.zhoujinniao.util;

import java.util.ArrayList;

public class ParsingUtils {

	public static ArrayList<String> parsingString(String str) {
		ArrayList<String> list = new ArrayList<String>();

		for (int i = 0; i < str.length(); i++) {
			for (int j = i; j < str.length(); j++) {
				if (str.charAt(j) == '|') {
					list.add(str.substring(i, j));
					i = j+1;
				}
			}
		}

		return list;
	}
}
