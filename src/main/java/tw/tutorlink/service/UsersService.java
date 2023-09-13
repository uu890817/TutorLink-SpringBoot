package tw.tutorlink.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.UserDetail;
import tw.tutorlink.bean.Users;
import tw.tutorlink.dto.user.InfomationDTO;
import tw.tutorlink.repository.UserDetailDAO;
import tw.tutorlink.repository.UsersDAO;

@Service
public class UsersService {

	@Autowired
	private UsersDAO uDAO;

	@Autowired
	private UserDetailDAO udDAO;

	public Users googleLogin(String sub, String mail) {

		Users users = uDAO.findByGoogleSubId(sub);
		if (users != null) {
			// 不為空，驗證傳入資料及查詢的資料是否吻合
			if (users.getGoogleSubId().equals(sub) && users.getUserEmail().equals(mail)) {
				// 驗證成功，寫入登入時間
				UserDetail ud = users.getUserDetail();
				ud.setLastLoginTime(new Date());
				udDAO.save(ud);
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
			ud.setTeacherState(1);
			ud.setCreateDate(new Date());
			uDAO.save(add);
			users = uDAO.findByGoogleSubId(sub);
			return users;
		}
	}

	public Users findUsersByID(Integer uID) {
		return uDAO.findById(uID).get();
	}

	// 查詢個人資料使用
	public InfomationDTO findByIdDetail(Integer id) {
		Users user = uDAO.findByIdDetail(id);
		InfomationDTO iDTO = new InfomationDTO(user);
		System.out.println(iDTO.getUsersId());
		System.out.println(iDTO.getUserEmail());
		System.out.println(iDTO.getUserName());
		System.out.println(iDTO.getBirthday());
		System.out.println(iDTO.getCity());
		System.out.println(iDTO.getPhone());
		return iDTO;
	}

	public UserDetail setData(int uID, String name, String phone, String city, long birth) {

		Users user = uDAO.findById(uID);

		if (user != null) {
			UserDetail ud = user.getUserDetail();
			ud.setBirthday(birth);
			ud.setUserName(name);
			ud.setPhone(phone);
			ud.setCity(city);
			System.out.println(ud.getCity());
			udDAO.save(ud);
			return ud;
		}
		return null;
	}

	public String findbyIdAndPwd(int cookieid, String oldpwd, String newPwd, String newPwd2) {
		Users user = uDAO.findById(cookieid);

		System.out.println(oldpwd + " " + newPwd + " " + newPwd2);
//		if (user != null) {
//			if (oldpwd != user.getUserPassword()||newPwd!=(newPwd2)) {
//				return "fail";
//			}
//		}
		user.setUserPassword(newPwd);
		uDAO.save(user);
		return "update";
	}

	public Users checkMail(String mail) {
		return uDAO.findByMail(mail);
	}

	public Users register(String name, String mail, String pwd) {
		System.out.println(name);
		System.out.println(mail);
		System.out.println(pwd);
		if (uDAO.findByMail(mail) == null) {
			Users user = new Users();
			UserDetail ud = new UserDetail();
			ud.setUsers(user);
			user.setUserAccount(mail);
			user.setUserEmail(mail);
			user.getUserDetail().setUserName(name);
			user.setUserPassword(pwd);
			user.setUserType(1);
			uDAO.save(user);
			return user;
		}
		return null;
	}
	// 錯誤代碼
	// 100 信箱不存在或者信箱輸入錯誤
	// 101 透過信箱跟密碼取得的唯一值ID不同
	// 102 密碼錯誤，請重新輸入
	// 103 資料正確，進行登入後續動作

	public String normalLogin(String mail, String pwd) {
		System.out.println("密碼是: " + pwd);
		Users usermail = uDAO.findByMail(mail);
		Users userpwd = uDAO.findByPwd(pwd, mail);

		if (usermail != null) {
			if (usermail.getUsersId() == userpwd.getUsersId()) {
				// id相同代表同一個user可以驗證資料
				if (usermail.getUserEmail() == userpwd.getUserEmail()) {
					// 先驗證信箱是否為同樣的，照理信箱要是相同的
					if (usermail.getUserPassword() == userpwd.getUserPassword()) {
						// 先驗證撈出來的兩組密碼吻合，在各別與前端送的密碼做驗證
						if (usermail.getUserPassword().equals(pwd) && userpwd.getUserPassword().equals(pwd)) {
							return "103";
						}
						return "102";
					}
				}
			}
			// 檢查兩者取出來的id是不是同一組，進而驗證屬於同一個User
			return "101";
		}
		// 信箱不存在或者信箱輸入錯誤
		return "100";
	}

// ----- 管理者頁面查詢全部 -----
	public List<Users> findAllUsers(int start, int rows) {
		if (start == 0 && rows == 0) {
			return uDAO.findAll();
		}
//		return uDAO.findPagin(start,rows);
		return null;
	}

	public long count() {
		return uDAO.count();
	}

}
