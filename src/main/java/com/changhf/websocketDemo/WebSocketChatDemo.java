package com.changhf.websocketDemo;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class WebSocketChatDemo {
	@OnOpen
	public void open(Session session){
		System.out.println("Guest"+session.getId()+": connected");
	}
	@OnMessage
	public void message(Session session,String msg){
		msg = "Guest"+session.getId()+": "+msg;
		System.out.println(msg);
		try {
			session.getBasicRemote().sendText(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@OnClose
	public void close(Session session){
		System.out.println("Guest"+session.getId()+": closed");
	}
}
