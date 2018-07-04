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
	private Integer code;
	/**
	 * 返回消息
	 */
	private String msg = "";
	/**
	 * 其他对象
	 */
	private Object data = null;// 返回其他对象信息

	public void setSuccess() {
		this.code = 0;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
