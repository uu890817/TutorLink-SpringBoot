package tw.tutorlink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tw.tutorlink.bean.WebSocketChatRoom;

public interface WebSocketChatRoomDAO extends JpaRepository<WebSocketChatRoom, Integer> {

	
	@Query("FROM WebSocketChatRoom cr WHERE cr.users.usersId = :uId")
	public List<WebSocketChatRoom> chatRooms(Integer uId);
	
	
}
