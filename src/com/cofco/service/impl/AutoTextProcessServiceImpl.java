package com.cofco.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cofco.service.AutoTextProcessService;
import com.cofco.util.BaseUtil;
import com.cofco.util.ReturnMessagePacking;

@Service("AutoTextProcessService")
public class AutoTextProcessServiceImpl implements AutoTextProcessService {
	private static final Logger logger = Logger.getLogger(AutoTextProcessServiceImpl.class);
	private static AutoTextProcessService autoTextProcessService = null;

	public static synchronized AutoTextProcessService getInstance() {
		if (autoTextProcessService == null) {
			autoTextProcessService = new AutoTextProcessServiceImpl();
		}
		return autoTextProcessService;
	}

	@Override
	public ReturnMessagePacking getResultText(String beginSignString, String endSignString, String text) {
		/* 预处理 */
		if (beginSignString == null || beginSignString.equals("")) {
			logger.warn("开始标记为空");
			return new ReturnMessagePacking(false, "开始标记为空");
		} else if (endSignString == null || endSignString.equals("")) {
			logger.warn("结束标记为空");
			return new ReturnMessagePacking(false, "结束标记为空");
		} else if (text == null || text.equals("")) {
			logger.warn("待处理文本为空");
			return new ReturnMessagePacking(false, "待处理文本为空");
		} else if (beginSignString.equals(endSignString)) {
			logger.warn("开始标记和结束标记不能一致");
			return new ReturnMessagePacking(false, "开始标记和结束标记不能一致");
		} else if (beginSignString.contains(endSignString) || endSignString.contains(beginSignString)) {
			logger.warn("开始标记和结束标记不能存在包含关系");
			return new ReturnMessagePacking(false, "开始标记和结束标记不能存在包含关系");
		}
		/* 开始标记和结束标记标准化(去除全角半角空格,制表符,分页符) */
		String beginSign = BaseUtil.convertKey(beginSignString);
		String endSign = BaseUtil.convertKey(endSignString);
		try {
			List<String> containTextList = new ArrayList<String>();
			/* 拆分处理 */
			String[] subText = text.split(beginSign);
			/*
			 * 判断拆分的第一部分是否需要处理,即开始标记是否开头,解决开始标记非文本开始,但第一个开始标记前(拆分第一部分中)有结尾标记的问题
			 */
			int beginSignIndex = text.indexOf(beginSignString);
			/* 筛选每一个拆分结果结束标记前的文本 */
			for (int i = (beginSignIndex == 0 ? 1 : 0); i < subText.length; i++) {
				if (i == 0 && beginSignIndex >= 0 && text.substring(0, beginSignIndex).contains(beginSignString)) {
					if (subText[i].contains(endSignString)) {
						String containText = subText[i].split(endSign)[0];
						containTextList.add(containText);
					}
				} else if (i > 0 && subText[i].contains(endSignString)) {
					String containText = subText[i].split(endSign)[0];
					containTextList.add(containText);
				}
			}
			return new ReturnMessagePacking(true, containTextList);
		} catch (Exception e) {
			if (e.equals(PatternSyntaxException.class)) {
				logger.error(e.toString());
				return new ReturnMessagePacking(false, "转换过程出错");/* 拆分过程出错 */
			} else {
				logger.error(e.toString());
				return new ReturnMessagePacking(false, "转换过程出错 : " + e.toString());/* 转换过程出错 */
			}
		}
	}
}
