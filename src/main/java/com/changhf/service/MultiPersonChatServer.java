package com.changhf.service;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private static Map<String, Session> sessionMap = new HashMap<String, Session>();
	private String username = null;

	@OnOpen
	public void open(Session session) {
		username = session.getQueryString();
		username = username.split("=")[1];
		ss.add(session);
		sessionMap.put(username, session);
		usernames.add(username);
		// 用户进入通知
		String msg = "欢迎" + username + "进入聊天室";
		Message message = new Message();
		// message.setMsgType(MessageTypeEnum.NOTICE.getCode());
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
		message.setMsg(this.username + "离开了");
		message.setTo(usernames);
		broadcast(ss, JSON.toJSONString(message));
	}

	@OnMessage
	public void message(Session session, String msg) {
		ContentData data = JSON.parseObject(msg, ContentData.class);
		int msgType = data.getMsgType();
		if (msgType == MessageTypeEnum.TEXT_MSG.getCode()) {
			//单聊
			Session to_session = sessionMap.get(data.getTo());
			List<Session> ss = new ArrayList<Session>();
			ss.add(to_session);
			ss.add(sessionMap.get(username));
			Message message = new Message();
			message.setMsg("<font style='color:red;'>"+this.username+" "+LocalTime.now().withNano(0)+"<br>"+data.getMsgData()+"</font>");
			broadcast(ss, JSON.toJSONString(message));
		} else {
			// 消息发送
			Message message = new Message();
			message.setMsg(username, data.getMsgData());
			message.setTo(usernames);
			broadcast(ss, JSON.toJSONString(message));
		}
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
