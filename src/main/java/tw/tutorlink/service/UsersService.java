package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.UserDetail;
import tw.tutorlink.bean.Users;
import tw.tutorlink.repository.UserDetailDAO;
import tw.tutorlink.repository.UsersDAO;

@Service
public class UsersService {

	@Autowired
	private UsersDAO uDAO;

	@Autowired
	private UserDetailDAO udDAO;

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
			UserDetail ud = new UserDetail();
			add.setUserEmail(mail);
			add.setUserAccount(mail);
			add.setGoogleSubId(sub);
			add.setUserType(1);
			ud.setUsers(add);
			uDAO.save(add);
			users = uDAO.findByGoogleSubId(sub);
			return users;
		}
	}

	public Users findUsersByID(Integer uID) {
		return uDAO.findById(uID).get();
	}

	public Users findByIdDetail(Integer id) {
		return uDAO.findByIdDetail(id);
	}
	public UserDetail setData(int uID, String name, String phone, String city, int birth) {

		Users user = uDAO.findById(uID);

		if (user != null) {
			UserDetail ud = user.getUserDetail();
			ud.setUserName(name);
			ud.setPhone(phone);
			ud.setCity(city);
			udDAO.save(ud);
			return ud;
		}
		return null;
	}
}
