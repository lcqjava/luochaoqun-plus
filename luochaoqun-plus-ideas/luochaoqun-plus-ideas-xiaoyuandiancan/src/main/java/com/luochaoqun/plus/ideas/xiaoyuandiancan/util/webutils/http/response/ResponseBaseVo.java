package com.luochaoqun.plus.ideas.xiaoyuandiancan.util.webutils.http.response;

import java.io.Serializable;

import com.xiaoaiqinqin.common.serialize.JsonUtil;

public class ResponseBaseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7825970926394886718L;
	protected Integer code;
	protected String message;

	public ResponseBaseVo() {
	}

	public ResponseBaseVo(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public static ResponseBaseVo success() {
		return new ResponseBaseVo(ResponseConstant.SUCCESS_CODE, ResponseConstant.SUCCESS_MSG);
	}

	public static ResponseBaseVo argErro(String message) {
		return new ResponseBaseVo(ResponseConstant.ARG_ERROR_CODE, message);
	}

	public static ResponseBaseVo loginTimeOut() {
		return new ResponseBaseVo(ResponseConstant.LOGIN_TIME_OUT_CODE, ResponseConstant.LOGIN_TIME_OUT_MSG);
	}

	public static ResponseBaseVo systemError(String msg) {
		return new ResponseBaseVo(ResponseConstant.SYSTEM_ERROR_CODE,msg);
	}

	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String toString() {
		return JsonUtil.toJsonString(this);
	}

}
