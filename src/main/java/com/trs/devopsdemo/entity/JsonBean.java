package com.trs.devopsdemo.entity;

public class JsonBean {

	// 状态码 0表示成功，-1表示失败，-999表示请求失败
	private Integer code;
	// 响应信息
	private String msg;
	// 数据对象
	private Object data;

	public JsonBean() {
	}

	public JsonBean(Integer code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
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

}
