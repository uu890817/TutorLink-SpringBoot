package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.Users;
import tw.tutorlink.repository.UsersDAO;

@Service
public class UsersService {

	@Autowired
	private UsersDAO uDAO;

	public Users login(String strSub) {

		Users users = uDAO.findByGoogleSubId(strSub);
		return users;
	}
	
	public Users findUsersByID(Integer uID) {
		return uDAO.findById(uID).get();
	}
}
