package com.cofco.service;

import com.cofco.util.ReturnMessagePacking;

public interface AutoTextProcessService {
	/**
	 * 将输入转化为输出处理过程
	 * 
	 * @param beginSign
	 *            拆分开始标记
	 * @param endSign
	 *            拆分结束标记
	 * @param text
	 *            待处理字符串
	 * @return 处理结果
	 */
	public ReturnMessagePacking getResultText(String beginSign, String endSign, String text);
}
