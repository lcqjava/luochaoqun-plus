package com.luochaoqun.plus.ideas.xiaoyuandiancan.util.webutils.http.response;

import java.io.Serializable;


public class ResponseDataVo<T> extends ResponseBaseVo implements Serializable {
	private static final long serialVersionUID = -8389534598865802502L;
	private T data;

	public ResponseDataVo() {
	}

	public ResponseDataVo(T data, Integer code, String message) {
		super(code, message);
		this.data = data;
	}
	public static <T> ResponseDataVo<T> inventoryNotEnough(String msg){
		return new ResponseDataVo<T>(null,ResponseConstant.INVENTORY_NOT_ENOUGH, msg);
	}
	public static <T> ResponseDataVo<T> success(T t) {
		return new ResponseDataVo<T>(t, ResponseConstant.SUCCESS_CODE, "成功");
	}

	public static <T> ResponseDataVo<T> argError(String message) {
		return new ResponseDataVo<T>(null, ResponseConstant.ARG_ERROR_CODE, message);
	}
	public static <T> ResponseDataVo<T> timeOut() {
		return new ResponseDataVo<T>(null, ResponseConstant.LOGIN_TIME_OUT_CODE, ResponseConstant.LOGIN_TIME_OUT_MSG);
	}
	public static <T> ResponseDataVo<T> sysError(String message) {
		return new ResponseDataVo<T>( null, Integer.valueOf(4), message);
	}

	public T getData() {
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
