package com.changhf.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSON;
import com.changhf.common.MessageTypeEnum;
import com.changhf.domain.Message;

/**
 * 多人聊天
 * 
 * @author changhf
 *
 */
@ServerEndpoint("/multichat")
public class MultiPersonChatServer {
	private static List<Session> ss = new ArrayList<Session>();
	private static List<String> usernames = new ArrayList<String>();
	private String username = null;

	@OnOpen
	public void open(Session session) {
		username = session.getQueryString();
		username = username.split("=")[1];
		ss.add(session);
		usernames.add(username);
		// 用户进入通知
		String msg = "欢迎" + username + "进入聊天室";
		Message message = new Message();
		message.setMsgType(MessageTypeEnum.NOTICE.getCode());
		message.setMsg(msg);
		message.setTo(usernames);
		broadcast(ss, JSON.toJSONString(message));
	}

	@OnClose
	public void close(Session session) {
		ss.remove(session);
		usernames.remove(username);
		// 用户退出通知
		Message message = new Message();
		message.setMsgType(MessageTypeEnum.NOTICE.getCode());
		message.setMsg(this.username + "离开了");
		message.setTo(usernames);
		broadcast(ss, JSON.toJSONString(message));
	}

	@OnMessage
	public void message(Session session, String msg) {
		msg = this.username + ":" + msg;
		// 消息发送
		Message message = new Message();
		message.setMsgType(MessageTypeEnum.NOTICE.getCode());
		message.setMsg(msg);
		message.setTo(usernames);
		broadcast(ss, JSON.toJSONString(message));
	}

	/**
	 * 广播发送
	 * 
	 * @param sessions
	 * @param msg
	 */
	public void broadcast(List<Session> sessions, String msg) {
		try {
			for (int i = 0; i < sessions.size(); i++) {
				sessions.get(i).getBasicRemote().sendText(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
