package com.carol.admin.base;

/**
 * 封装json结果集
 * 
 * @author chris
 *
 */
public class JsonResult {
	/**
	 * 返回是否成功
	 */
	private Boolean success = true;
	/**
	 * 返回消息
	 */
	private String msg = "";
	/**
	 * 其他对象
	 */
	private Object obj = null;// 返回其他对象信息

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
