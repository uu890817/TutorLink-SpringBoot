package tw.tutorlink.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="WebSocketChatMember")
public class WebSocketChatMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ChatMemberId")
	private Integer chatMemberId;
	
	@ManyToOne
	@JoinColumn(name = "ChatRoomId", referencedColumnName = "chatRoomId")
	private WebSocketChatRoom chatRoom;
	
	@ManyToOne
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId")
	private Users users;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Type")
	private Integer type;
	
	
}
