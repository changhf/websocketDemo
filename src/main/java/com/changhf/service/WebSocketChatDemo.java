package com.changhf.service;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @ServerEndpoint 该注解相当于以前的Servlet，表示一个websocket服务
 * @author changhf
 *
 */
@ServerEndpoint("/chat")
public class WebSocketChatDemo {
	@OnOpen
	public void open(Session session) {
		System.out.println("Guest" + session.getId() + ": connected");
	}

	@OnMessage
	public void message(Session session, String msg) {
		// 当前总人数
		// session_list = session.getOpenSessions();
		msg = "Guest" + session.getId() + ": " + msg;
		System.out.println(msg);
		try {
			session.getBasicRemote().sendText(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@OnClose
	public void close(Session session) {
		System.out.println("Guest" + session.getId() + ": closed");
	}
}
