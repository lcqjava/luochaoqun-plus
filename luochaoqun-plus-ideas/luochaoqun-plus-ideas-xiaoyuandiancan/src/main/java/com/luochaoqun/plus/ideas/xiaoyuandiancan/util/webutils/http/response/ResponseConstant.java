package com.luochaoqun.plus.ideas.xiaoyuandiancan.util.webutils.http.response;

/**
 * 请求返回的状态码
 * @author luo.cq
 * @date   2017-11-17 16:26
 */
public interface ResponseConstant {
	/**成功**/
	Integer SUCCESS_CODE = 200;
	/**系统异常**/
	Integer SYSTEM_ERROR_CODE = 201;
	/**参数异常**/
	Integer ARG_ERROR_CODE = 202;
	/**登录超时**/
	Integer LOGIN_TIME_OUT_CODE = 203;
	/**库存不足**/
	Integer INVENTORY_NOT_ENOUGH = 204;
	
	/**未知异常**/
	Integer OTHER_ERROR_CODE = 400;
	
	/**返回信息：成功**/
	String SUCCESS_MSG = "成功";
	String SYSTEM_ERROR_MSG = "系统异常";
	String ARG_ERROR_MSG = "参数异常";
	String LOGIN_TIME_OUT_MSG = "登录超时";
	String OTHER_ERROR_MSG = "未知异常";
	String INVENTORY_NOT_ENOUGH_MSG = "库存不足";
	
}
