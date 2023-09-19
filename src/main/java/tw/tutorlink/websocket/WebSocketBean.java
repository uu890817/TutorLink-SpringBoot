package tw.tutorlink.websocket;

import org.springframework.web.socket.WebSocketSession;

public class WebSocketBean {

	private WebSocketSession wSession;
	private Integer clientId;
	
	public WebSocketSession getwSession() {
		return wSession;
	}
	public void setwSession(WebSocketSession wSession) {
		this.wSession = wSession;
	}
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	
	
}
