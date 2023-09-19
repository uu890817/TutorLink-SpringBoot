package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.WebSocketChatRoom;

public interface WebSocketChatRoomDAO extends JpaRepository<WebSocketChatRoom, Integer> {

}
