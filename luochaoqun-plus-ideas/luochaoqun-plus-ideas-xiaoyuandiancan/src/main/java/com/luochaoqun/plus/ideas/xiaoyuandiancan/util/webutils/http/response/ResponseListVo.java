package com.luochaoqun.plus.ideas.xiaoyuandiancan.util.webutils.http.response;

import java.io.Serializable;
import java.util.List;


public class ResponseListVo<T> extends ResponseBaseVo implements Serializable {
	private static final long serialVersionUID = 19289478919077855L;
	private List<T> list;

	public ResponseListVo(List<T> list, Integer code, String message) {
		super(code, message);
		this.list = list;
	}

	public static <T> ResponseListVo<T> success(List<T> list) {
		return new ResponseListVo<T>(list, ResponseConstant.SUCCESS_CODE, "成功");
	}

	public static <T> ResponseListVo<T> argError(String message) {
		return new ResponseListVo<T>((List<T>) null, ResponseConstant.ARG_ERROR_CODE, message);
	}

	public List<T> getList() {
		return this.list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
