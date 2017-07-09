package com.changhf.service;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/multichat")
public class MultiPersonChatServer {
	@OnOpen
	public void open(Session session){
		
	}
	@OnClose
	public void close(Session session){
		
	}
	@OnMessage
	public void message(Session session,String msg){
		
	}
}
