package com.luochaoqun.plus.ideas.xiaoyuandiancan.util.webutils.http.response;

import java.io.Serializable;

public class ResponsePageVo<T> extends ResponseBaseVo implements Serializable {
	private static final long serialVersionUID = -3051305136207861351L;
	private SimplePageVo<T> pageVo;

	public ResponsePageVo(SimplePageVo<T> pageVo, Integer code, String message) {
		super(code, message);
		this.pageVo = pageVo;
	}

	public static <T> ResponsePageVo<T> success(SimplePageVo<T> pageVo) {
		return new ResponsePageVo<T>(pageVo, ResponseConstant.SUCCESS_CODE, "成功");
	}

	public static <T> ResponsePageVo<T> argError(String message) {
		return new ResponsePageVo<T>((SimplePageVo<T>) null, ResponseConstant.ARG_ERROR_CODE, message);
	}

	public SimplePageVo<T> getPageVo() {
		return this.pageVo;
	}

	public void setPageVo(SimplePageVo<T> pageVo) {
		this.pageVo = pageVo;
	}
}
