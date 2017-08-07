package com.cofco.util.enumclass;

/**
 * 各种自定义异常的枚举类
 * 
 * @author mona
 */
public enum ExceptionType {
	/* 数据库通用异常 */
	UNKNOW("未知"), ISEXISTS("记录ID已存在"), UPDATEISNULL("没有查询或更新记录"),
	/* 系统异常转换 */
	ClassCastException("转换类型异常");

	private final String name;

	private ExceptionType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
