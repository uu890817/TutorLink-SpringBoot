package tw.tutorlink.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
				ud.setLastLoginTime(users.getUserDetail().getNewLoginTime());
				ud.setNewLoginTime(new Date());
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
		// 密碼加密
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String passwordEncoder = bCryptPasswordEncoder.encode(pwd);

		if (uDAO.findByMail(mail) == null) {
			Users user = new Users();
			UserDetail ud = new UserDetail();
			ud.setUsers(user);
			user.setUserEmail(mail);
			user.getUserDetail().setUserName(name);
			user.setUserPassword(passwordEncoder);
			user.setUserType(1);
			ud.setTeacherState(1);
			uDAO.save(user);
			udDAO.save(ud);
			return user;
		}
		return null;
	}

	// 代碼
	// 100 信箱不存在或者信箱輸入錯誤
	// 101 密碼錯誤，請重新輸入
	// 102 資料正確，進行登入後續動作

	public String normalLogin(String mail, String pwd) {
		System.out.println("密碼是: " + pwd);
		Users usermail = uDAO.findByMail(mail);
		if (usermail == null) {
			return "100";
		}
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		// 比對
		boolean matches = bCryptPasswordEncoder.matches(pwd, usermail.getUserPassword());

		// 檢查輸入的信箱是否存在資料庫
		if (usermail != null) {
			// 檢查輸入的密碼是否與資料庫解密的密碼相符
			if (matches) {
				// 寫入登入時間
				usermail.getUserDetail().setLastLoginTime(usermail.getUserDetail().getNewLoginTime());
				usermail.getUserDetail().setNewLoginTime(new Date());
				uDAO.save(usermail);
				return "102";
			}
		}
		return "101";
	}

// ----- 管理者頁面查詢全部 -----
	// 分頁使用
	public List<Users> findAllUsers(int page, int rows) {
		if (page == 0 && rows == 0) {
			return uDAO.findAll();
		}
		Pageable pageable = PageRequest.of(page, rows);
		Page<Users> result = uDAO.findAll(pageable);
		return result.getContent();
	}

	public long count() {
		return uDAO.count();
	}

	public Users forgetMail(String mail, int randomNumber) {

		Users result = uDAO.findByMail(mail);
		if (result != null) {
			result.setRamdonVerify(randomNumber);
			result.setExpiredTime(new Date());
			uDAO.save(result);
			return result;
		}
		return null;
	}

}
