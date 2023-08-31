package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.Users;
import tw.tutorlink.repository.UsersDAO;

@Service
public class UsersService {

	@Autowired
	private UsersDAO uDAO;

	public Users login(String sub, String mail) {

		Users users = uDAO.findByGoogleSubId(sub);
		if (users != null) {
			return users;
		} else {
			Users add = new Users();
			add.setUserEmail(mail);
			add.setGoogleSubId(sub);
			add.setUserType(1);
			uDAO.save(add);
			users = uDAO.findByGoogleSubId(sub);
			return users;
		}
	}

	public Users findUsersByID(Integer uID) {
		return uDAO.findById(uID).get();
	}

}
