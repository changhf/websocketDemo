package com.changhf.domain;

import java.time.LocalTime;
import java.util.List;

public class Message {
	private String msg;
	private List<String> to;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setMsg(String username,String msg) {
		this.msg = username+" "+LocalTime.now().withNano(0)+"<br>"+msg;
	}
	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

}
