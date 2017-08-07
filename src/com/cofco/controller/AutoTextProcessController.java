package com.cofco.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cofco.service.AutoTextProcessService;
import com.cofco.service.impl.AutoTextProcessServiceImpl;
import com.cofco.util.ReturnMessagePacking;
import com.fasterxml.jackson.annotation.JsonInclude;

@Controller
@RequestMapping("/*")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AutoTextProcessController {
	@Qualifier("AutoTextProcessService")
	@Autowired

	private AutoTextProcessService publicService = AutoTextProcessServiceImpl.getInstance();

	/**
	 * 获取处理结果接口
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "getResultText.do", method = RequestMethod.POST)
	@ResponseBody
	public ReturnMessagePacking getResultTextDo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String beginSign = request.getParameter("beginSign");
		String endSign = request.getParameter("endSign");
		String text = request.getParameter("text");

		ReturnMessagePacking result = publicService.getResultText(beginSign, endSign, text);
		return result;
	}
}
