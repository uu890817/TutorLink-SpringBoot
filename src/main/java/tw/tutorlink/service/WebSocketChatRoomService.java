package tw.tutorlink.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.WebSocketChatMember;
import tw.tutorlink.bean.WebSocketChatRoom;
import tw.tutorlink.dto.websocket.ChatRoomDTO;
import tw.tutorlink.repository.WebSocketChatRoomDAO;

@Service
public class WebSocketChatRoomService {

	@Autowired
	private WebSocketChatRoomDAO crDAO;
	
	public List<ChatRoomDTO> getMyChatRoom(Integer uId) {
		List<ChatRoomDTO> crDTOs = new ArrayList<>();
		
		for(WebSocketChatRoom cr: crDAO.chatRooms(uId)) {
			ChatRoomDTO cDTO = new ChatRoomDTO(cr);
	
			for(WebSocketChatMember cm: cr.getChatMember()) {
				if(cm.getUsers().getUsersId() != uId) {
					cDTO.setMemberId(cm.getUsers().getUsersId());
					cDTO.setMemberName(cm.getUsers().getUserDetailUserName());
				}
			}
			crDTOs.add(cDTO);
		}
		return crDTOs;
	}
	
	
	
	
	
	
	
	
	
}
