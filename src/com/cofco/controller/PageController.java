package com.cofco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.annotation.JsonInclude;

@Controller
@RequestMapping("/*")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PageController {
	@Qualifier("PageService")
	@Autowired

	/**
	 * 主页页面
	 * 
	 * @return 页面地址
	 */
	@RequestMapping(value = "index.html", method = RequestMethod.GET)
	public String index() {
		return "pages/index";
	}

	/**
	 * 自动化处理页面
	 * 
	 * @return 页面地址
	 */
	@RequestMapping(value = "AutoTextProcess.html", method = RequestMethod.GET)
	public String getAutoTextProcessPage() {
		return "pages/AutoTextProcess";
	}
}
