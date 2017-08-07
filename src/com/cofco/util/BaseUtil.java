package com.cofco.util;

public class BaseUtil {
	private static BaseUtil baseUtil = null;

	public static synchronized BaseUtil getInstance() {
		if (baseUtil == null) {
			baseUtil = new BaseUtil();
		}
		return baseUtil;
	}

	public static String convertKey(String string) {
		String result = string;
		/* split关键字转义 */
		if (result.contains("\\")) {
			result = result.replaceAll("\\\\", "\\\\\\\\");
		}
		if (result.contains("[")) {
			result = result.replaceAll("\\[", "\\\\[");
		}
		if (result.contains("]")) {
			result = result.replaceAll("\\]", "\\\\]");
		}
		if (result.contains(".")) {
			result = result.replaceAll("\\.", "[\\\\.]");
		}
		if (result.contains("|")) {
			System.err.println("result : " + result);
			result = result.replaceAll("\\|", "\\\\|");
			System.err.println("result : " + result);
		}
		if (result.contains("*")) {
			result = result.replaceAll("\\*", "\\\\*");
		}
		/* 删除空格 */
		result = removeAllBlank(result);
		return result;
	}

	/**
	 * 去除字符串中所包含的空格（包括:空格(全角，半角)、制表符、换页符等）
	 * 
	 * @param string
	 *            待处理字符串
	 * @return
	 */
	public static String removeAllBlank(String string) {
		String result = "";
		if (string != null && !string.equals("")) {
			result = string.replaceAll("　", "").replaceAll(" ", "").replaceAll("\\s*", "");
		}
		return result;
	}
}
