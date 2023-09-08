package tw.tutorlink.service;

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
	
	// 查詢個人資料使用
	public InfomationDTO findByIdDetail(Integer id) {
		Users user = uDAO.findByIdDetail(id);
		InfomationDTO iDTO = new InfomationDTO(user);
		System.out.println(iDTO.getCity());
		System.out.println(iDTO.getPhone());
		System.out.println(iDTO.getUserEmail());
		System.out.println(iDTO.getUserName());
		System.out.println(iDTO.getBirthday());
		System.out.println(iDTO.getUsersId());
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
			Users user= new Users();
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
}
