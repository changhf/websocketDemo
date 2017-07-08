package com.changhf.websocketDemo;

import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

/**
 * ServerApplicationConfig 实现该接口，项目启动时会自动执行
 * 
 * @author changhf
 *
 */
public class WebsocketServer implements ServerApplicationConfig {

	public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scan) {
		System.out.println("scan initializing.." + scan.size());
		return scan;
	}

	public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
