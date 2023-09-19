package tw.tutorlink.bean;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class WebSocketChatRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ChatRoomId")
	private Integer chatRoomId;

	@Column(name = "Name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId")
	@Column(name = "OwnerId")
	private Users owner;
	
	@Column(name = "Type")
	private Integer type;
	
	@Column(name="CreateDate")
	private Date createDate;
	
	//----------------------------------------------
	
	@OneToMany(mappedBy = "chatRoomId", cascade = CascadeType.ALL)
	private List<WebSocketChatMember> chatMember;
	
	
}
