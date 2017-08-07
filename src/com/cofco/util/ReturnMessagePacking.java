package com.cofco.util;

/**
 * 因为错误消息会在Serice层捕获，且服务器与网页客户端之间只能返回一个对象，所以加一层封装
 * 
 * @author mona
 */
public class ReturnMessagePacking {
	private boolean isSuccess;
	private String errMessage;
	private Object returnObject;

	/**
	 * 返回成功或失败
	 * 
	 * @param isSuccess
	 */
	public ReturnMessagePacking(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	/**
	 * 返回失败和错误信息
	 * 
	 * @param isSuccess
	 * @param errMessage
	 */
	public ReturnMessagePacking(boolean isSuccess, String errMessage) {
		this.isSuccess = isSuccess;
		this.errMessage = errMessage;
	}

	/**
	 * 返回成功和对象 或 返回失败和异常
	 * 
	 * @param isSuccess
	 * @param object
	 */
	public ReturnMessagePacking(boolean isSuccess, Object object) {
		this.isSuccess = isSuccess;
		this.returnObject = object;
	}

	public boolean getIsSuccess() {
		return isSuccess;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public Object getReturnObject() {
		return returnObject;
	}
}
