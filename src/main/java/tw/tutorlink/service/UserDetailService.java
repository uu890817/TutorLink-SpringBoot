package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.UserDetail;
import tw.tutorlink.repository.UserDetailDAO;

@Service
public class UserDetailService {

	@Autowired
	private UserDetailDAO udDAO;
	
	public String getUserName(Integer userId) {
		return udDAO.findUserNameByUserId(userId);
	}
	
	public UserDetail getUserDetail(Integer userId) {
		return udDAO.findByUsers_usersId(userId);
	}
}
