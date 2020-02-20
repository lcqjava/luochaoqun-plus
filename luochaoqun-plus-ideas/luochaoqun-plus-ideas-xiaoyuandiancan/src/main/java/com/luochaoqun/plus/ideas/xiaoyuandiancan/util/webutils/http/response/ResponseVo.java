package com.luochaoqun.plus.ideas.xiaoyuandiancan.util.webutils.http.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ResponseVo extends HashMap<String, Object> {

	private static String PAGE_NO = "pageNo";
	private static String PAGE_SIZE = "pageSize";
	private static String DATA = "data";
	private static String RESULT_CODE = "resultCode";
	private static String TOTAL = "total";
	private static String TOTAL_PAGE = "totalPage";
	private static String MSG = "msg";
	/** 成功 **/
	public static final int RESULT_SUCCESS = 0;
	/** 成功 **/
	public static final int RESULT_FAILED = 1;
	/** 没有登录 **/
	public static final int RESULT_NO_LOGIN = 2;
	private static Map<String, Object> map = new HashMap<String, Object>();

	/**
	 * 
	 */
	private static final long serialVersionUID = -8296539362846034273L;

	/**
	 * 成功
	 * 
	 * @param data
	 * @param pageNo
	 * @param pagSize
	 * @param total
	 * @param totalPage
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> success(List data, Integer pageNo, Integer pagSize, Integer total,
			Integer totalPage) {

		putVal(RESULT_SUCCESS, data, pageNo, pagSize, total, totalPage, null);

		return map;
	}

	/**
	 * 失败
	 * 
	 * @param msg
	 * @return
	 */
	public static Map<String, Object> error(String msg) {

		putVal(RESULT_FAILED, null, null, null, null, null, msg);

		return map;
	}

	@SuppressWarnings("rawtypes")
	private static void putVal(int resultCode, List data, Integer pageNo, Integer pagSize, Integer total,
			Integer totalPage, String msg) {
		map.put(RESULT_CODE, resultCode);
		map.put(DATA, data);
		map.put(PAGE_NO, pageNo);
		map.put(PAGE_SIZE, PAGE_SIZE);
		map.put(TOTAL, total);
		map.put(TOTAL_PAGE, totalPage);
		map.put(MSG, msg);
	}

	public static String getPAGE_NO() {
		return PAGE_NO;
	}

	public static void setPAGE_NO(String pAGE_NO) {
		PAGE_NO = pAGE_NO;
	}

	public static String getPAGE_SIZE() {
		return PAGE_SIZE;
	}

	public static void setPAGE_SIZE(String pAGE_SIZE) {
		PAGE_SIZE = pAGE_SIZE;
	}

	public static String getDATA() {
		return DATA;
	}

	public static void setDATA(String dATA) {
		DATA = dATA;
	}

	public static String getRESULT_CODE() {
		return RESULT_CODE;
	}

	public static void setRESULT_CODE(String rESULT_CODE) {
		RESULT_CODE = rESULT_CODE;
	}

	public static String getTOTAL() {
		return TOTAL;
	}

	public static void setTOTAL(String tOTAL) {
		TOTAL = tOTAL;
	}

	public static String getTOTAL_PAGE() {
		return TOTAL_PAGE;
	}

	public static void setTOTAL_PAGE(String tOTAL_PAGE) {
		TOTAL_PAGE = tOTAL_PAGE;
	}

	public static String getMSG() {
		return MSG;
	}

	public static void setMSG(String mSG) {
		MSG = mSG;
	}

	public static Map<String, Object> getMap() {
		return map;
	}

	public static void setMap(Map<String, Object> map) {
		ResponseVo.map = map;
	}

	public static int getResultSuccess() {
		return RESULT_SUCCESS;
	}

	public static int getResultFailed() {
		return RESULT_FAILED;
	}

	public static int getResultNoLogin() {
		return RESULT_NO_LOGIN;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static void main(String[] args) {
//		List<User> list = new ArrayList<User>();
//		list.add(new User(1, "1", "1"));
//		list.add(new User(2, "2", "2"));
//		String json = JSONUtils.toJSONString(list);
		
//		Map<String, Object> map = success(list, 1, 1, 1, 1);
//		System.out.println(map);
	}
}
