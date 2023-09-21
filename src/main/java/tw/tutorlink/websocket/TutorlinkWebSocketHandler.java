package tw.tutorlink.websocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.aspectj.bridge.Message;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class TutorlinkWebSocketHandler implements WebSocketHandler {

	private static final Map<String, WebSocketBean> webSocketBeanMap;
//	private static final AtomicInteger clientIdMaker;

	static {
		webSocketBeanMap = new ConcurrentHashMap<>();
//		clientIdMaker = new AtomicInteger(0);
	}

	
	private String splitUserId(WebSocketSession wSession) {
		String path = wSession.getUri().getPath();
		String[] splitPath = path.split("/");
		return splitPath[splitPath.length-1];
	}
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession wSession) throws Exception {
		// 客戶端與伺服器端建立連結執行
		WebSocketBean wsBean = new WebSocketBean();
		wsBean.setwSession(wSession);
		
//		wsBean.setClientId(clientIdMaker.getAndIncrement());//取值並+1
		webSocketBeanMap.put(splitUserId(wSession), wsBean);
//		wSession.sendMessage(new TextMessage("OKK"));
	}

	@Override
	public void handleMessage(WebSocketSession wSession, WebSocketMessage<?> message) throws Exception {
		// 客戶端對伺服器端發送消息
		String[] parts = ((String) message.getPayload()).split("<message>");
        if (parts.length == 2) {
            String targetUserId = parts[0];
            String content = parts[1];
            WebSocketSession targetSession = null;
            try {
                targetSession = webSocketBeanMap.get(targetUserId).getwSession();

            }catch (Exception e) {
//            	wSession.sendMessage(new TextMessage("對方不在線上"));
				// TODO: handle exception
			}
//           WebSocketSession targetSession = webSocketBeanMap.get(targetUserId).getwSession();
            if (targetSession != null && targetSession.isOpen()) {
                targetSession.sendMessage(new TextMessage(content));
            }
        }
	}

	@Override
	public void handleTransportError(WebSocketSession wSession, Throwable exception) throws Exception {
		// 錯誤處理
		if(wSession.isOpen()) {
			wSession.close();
		}
		webSocketBeanMap.remove(splitUserId(wSession));
	}

	@Override
	public void afterConnectionClosed(WebSocketSession wSession, CloseStatus closeStatus) throws Exception {
		// 關閉連接
		
		webSocketBeanMap.remove(splitUserId(wSession));
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

}
