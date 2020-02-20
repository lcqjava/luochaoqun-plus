package com.luochaoqun.plus.ideas.xiaoyuandiancan.util.webutils.http.response;

import java.io.Serializable;


/**
 * http返回消息转码
 * @author 
 * @param <T>
 */
public class HttpResponse<T> implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6217470123246226764L;


	/**
	 * 成功
	 */
	public static final int SUCCESS = 1;
	
	/**
	 * 返回结果
	 */
	private int result;
	
	/**
	 * 消息
	 */
	private String message;
	
	/**
	 * 总记录数
	 */
	private Integer count;
	
	/**
	 * 
	 */
	private T data;
	
	public int getResult() {
		return result;
	}


	public void setResult(int result) {
		this.result = result;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public T getData() {
		return data;
	}


	public void setData(T data) {
		this.data = data;
	}


	public Integer getCount() {
		return count;
	}


	public void setCount(Integer count) {
		this.count = count;
	}
	
}
