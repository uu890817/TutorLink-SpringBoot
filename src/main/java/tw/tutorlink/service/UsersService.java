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
			// 不為空，驗證傳入資料及查詢的資料是否吻合
			if (users.getGoogleSubId().equals(sub) && users.getUserEmail().equals(mail)) {
				// 吻合及回傳Bean
				return users;
			}
			// 不吻合回傳null，讓controller判定邏輯
			return null;
		} else {
			// 當為空值，代表不存在，即新增進資料庫做註冊使用
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
