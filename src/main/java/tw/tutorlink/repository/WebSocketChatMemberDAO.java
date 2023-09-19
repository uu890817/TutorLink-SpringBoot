package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.WebSocketChatMember;

public interface WebSocketChatMemberDAO extends JpaRepository<WebSocketChatMember, Integer> {

}
