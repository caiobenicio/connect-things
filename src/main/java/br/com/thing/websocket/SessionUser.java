package br.com.thing.websocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.WebSocketSession;

public class SessionUser {

	private static SessionUser instance;
	private Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
	private Map<Long, String> userSession = new ConcurrentHashMap<>();
	

	public SessionUser() {
	}

	public static synchronized SessionUser getInstance() {
		if (instance == null)
			instance = new SessionUser();

		return instance;
	}

	public Map<String, WebSocketSession> getSessions() {
		return sessions;
	}

	public Map<Long, String> getUserSession() {
		return userSession;
	}

}
