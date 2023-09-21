package tw.tutorlink.dto.websocket;

import tw.tutorlink.bean.WebSocketChatRoom;

public class ChatRoomDTO {
	private Integer chatRoomId;
	private String name;
	private Integer type;
	private Integer memberId;
	private String memberName;
	
	public ChatRoomDTO() {
	}
	
	public ChatRoomDTO(WebSocketChatRoom cr) {
		this.chatRoomId = cr.getChatRoomId();
		this.name = cr.getName();
		this.type = cr.getType();
		if(cr.getChatMember().size() != 0) {
			
		}
	}
	
	
	public Integer getChatRoomId() {
		return chatRoomId;
	}
	public void setChatRoomId(Integer chatRoomId) {
		this.chatRoomId = chatRoomId;
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

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	
	
	
	
	
}
