package tw.tutorlink.websocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class TutorlinkWebSocketHandler implements WebSocketHandler {

	private static final Map<String, WebSocketBean> webSocketBeanMap;
	private static final AtomicInteger clientIdMaker;

	static {
		webSocketBeanMap = new ConcurrentHashMap<>();
		clientIdMaker = new AtomicInteger(0);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession wSession) throws Exception {
		// 客戶端與伺服器端建立連結執行
		WebSocketBean wsBean = new WebSocketBean();
		wsBean.setwSession(wSession);
		wsBean.setClientId(clientIdMaker.getAndIncrement());//取值並+1
		webSocketBeanMap.put(wSession.getId(), wsBean);
	}

	@Override
	public void handleMessage(WebSocketSession wSession, WebSocketMessage<?> message) throws Exception {
		// 客戶端對伺服器端發送消息
		System.err.println(wSession);
		System.err.println(message);
		wSession.sendMessage(message);
	}

	@Override
	public void handleTransportError(WebSocketSession wSession, Throwable exception) throws Exception {
		// 錯誤處理
		if(wSession.isOpen()) {
			wSession.close();
		}
		webSocketBeanMap.remove(wSession.getId());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession wSession, CloseStatus closeStatus) throws Exception {
		// 關閉連接
		webSocketBeanMap.remove(wSession.getId());
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

}
