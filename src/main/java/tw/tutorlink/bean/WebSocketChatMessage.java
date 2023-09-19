package tw.tutorlink.bean;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="WebSocketChatMessage")
public class WebSocketChatMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MessageId")
	private Integer messageId;
	
	@ManyToOne
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId")
	private Users users;
	
	@ManyToOne
	@JoinColumn(name = "ChatRoomId", referencedColumnName = "chatRoomId")
	private WebSocketChatRoom chatRoom;
	
	@Column(name = "Content")
	private String content;
	
	@Column(name = "Type")
	private Integer type;
	
	@Column(name="CreateDate")
	private Date createDate;
	
}
