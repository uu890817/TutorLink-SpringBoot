package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.WebSocketChatMessage;

public interface WebSocketChatMessageDAO extends JpaRepository<WebSocketChatMessage, Integer> {

}
