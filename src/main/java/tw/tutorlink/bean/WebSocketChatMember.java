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

	public Integer getChatMemberId() {
		return chatMemberId;
	}

	public void setChatMemberId(Integer chatMemberId) {
		this.chatMemberId = chatMemberId;
	}

	public WebSocketChatRoom getChatRoom() {
		return chatRoom;
	}

	public void setChatRoom(WebSocketChatRoom chatRoom) {
		this.chatRoom = chatRoom;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
