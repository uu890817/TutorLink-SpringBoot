package tw.tutorlink.bean;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="WebSocketChatRoom")
public class WebSocketChatRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ChatRoomId")
	private Integer chatRoomId;

	@ManyToOne
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId")
	private Users users;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Type")
	private Integer type;
	
	@Column(name="CreateDate")
	private Date createDate;
	
	//----------------------------------------------
	
	@OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
	private List<WebSocketChatMember> chatMember;
	
	@OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
	private List<WebSocketChatMessage> message;

	//----------------------------------------------

	
	public Integer getChatRoomId() {
		return chatRoomId;
	}

	public void setChatRoomId(Integer chatRoomId) {
		this.chatRoomId = chatRoomId;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<WebSocketChatMember> getChatMember() {
		return chatMember;
	}

	public void setChatMember(List<WebSocketChatMember> chatMember) {
		this.chatMember = chatMember;
	}

	public List<WebSocketChatMessage> getMessage() {
		return message;
	}

	public void setMessage(List<WebSocketChatMessage> message) {
		this.message = message;
	}
	

	
	
	
	
	
	
	
	
	
}
