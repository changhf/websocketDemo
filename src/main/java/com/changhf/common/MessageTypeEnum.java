package com.changhf.common;
/**
 * 消息类型enum
 * @author changhf
 *
 */
public enum MessageTypeEnum {
	NOTICE(1, "通知"), TEXT_MSG(2, "文本消息");
	private int code;
	private String msg;

	MessageTypeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
