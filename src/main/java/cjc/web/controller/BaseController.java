package cjc.web.controller;

/**
 * BaseController.java
 * Copyright © 2015-2015
 *
 * @author pengdc
 * @create 2015年7月13日
 */

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import cjc.controller.common.H5Response;

/**
 * 
 * 
 * @author zwt
 * @date: 2015年11月4日 下午6:17:05
 */
public class BaseController {

	private final static Logger logger = LoggerFactory.getLogger(BaseController.class);

	public static H5Response succeed() {
		return getResponseData(true, null, "");
	}

	public static H5Response succeed(Object returnData) {
		return getResponseData(true, returnData, "");
	}

	public static H5Response failed() {
		return getResponseData(false, null, "");
	}

	public static H5Response failed(String msg) {
		return getResponseData(false, null, msg);
	}

	private static H5Response getResponseData(boolean status, Object data, String message) {
		H5Response obj = new H5Response();
		obj.setStatus(status);
		obj.setData(data);
		obj.setMsg(message);
		return obj;
	}

	/**
	 * 处理一般异常
	 * 
	 * @param ex
	 * @param request
	 * @return
	 *//*
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public H5Response handleException(Exception ex, HttpServletRequest request) {
		Throwable e = ExceptionUtils.getRootCause(ex);
		if (e == null) {
			e = ex;
		}
		System.out.println("系统异常"+e.getMessage());
		logger.error("System Error", e);
		return failed("系统繁忙");
	}*/

//	/**
//	 * 处理业务异常
//	 * 
//	 * @param ex
//	 * @param request
//	 * @return
//	 */
//	@ExceptionHandler(BizException.class)
//	@ResponseBody
//	public H5Response handleException(BizException ex, HttpServletRequest request) {
//		Throwable e = ExceptionUtils.getRootCause(ex);
//		if (e == null) {
//			e = ex;
//		}
//		logger.info("Business Exception", e);
//		return failed(e.getMessage());
//	}

	protected String getErrorsMessage(BindingResult result) {
		String message = "";
		List<ObjectError> list = result.getAllErrors();
		for (ObjectError objectError : list) {
			message = message + objectError.getDefaultMessage() + ";";
		}
		return message;
	}

}