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
	
}
