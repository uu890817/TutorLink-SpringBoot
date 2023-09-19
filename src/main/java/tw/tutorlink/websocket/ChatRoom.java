package tw.tutorlink.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ChatRoom extends TextWebSocketHandler {

    private Map<String, WebSocketSession> sessions = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 當連線建立時，將連線存儲到sessions中
        String userId = extractUserId(session);
        if (userId != null) {
            sessions.put(userId, session);
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        // 當接收到消息時，將消息傳送給目標用戶
        String[] parts = message.getPayload().split(":", 2);
        if (parts.length == 2) {
            String targetUserId = parts[0];
            String content = parts[1];
            WebSocketSession targetSession = sessions.get(targetUserId);
            if (targetSession != null && targetSession.isOpen()) {
                targetSession.sendMessage(new TextMessage(content));
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        // 當連線關閉時，從sessions中刪除連線
        String userId = extractUserId(session);
        if (userId != null) {
            sessions.remove(userId);
        }
    }

    private String extractUserId(WebSocketSession session) {
        // 從WebSocket連線的URL中解析出用戶ID
        String path = session.getUri().getPath();
        String[] pathParts = path.split("/");
        if (pathParts.length >= 3) {
            return pathParts[2];
        }
        return null;
    }
}