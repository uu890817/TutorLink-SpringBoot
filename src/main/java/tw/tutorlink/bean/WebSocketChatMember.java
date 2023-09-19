package tw.tutorlink.bean;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import tw.tutorlink.websocket.ChatRoom;

public class WebSocketChatMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ChatMemberId")
	private Integer ChatMemberId;
	
	@ManyToOne
	@JoinColumn(name = "ChatRoomId", referencedColumnName = "chatRoomId")
	private WebSocketChatRoom chatRoom;
	
	
	
}
