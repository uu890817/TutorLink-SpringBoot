package tw.tutorlink.dto.user;

import tw.tutorlink.bean.Users;

public class InfomationDTO {
	
	private Integer usersId;
	private String userName;
	private String userEmail;
	private Long birthday;
	private String phone;
	private String city;
	private String googletoken;
	public InfomationDTO() {
		
	}
	
	public InfomationDTO(Users user) {
		this.usersId=user.getUsersId();
		if(user.getUserDetail()!=null) {
			this.userName = user.getUserDetail().getUserName();
			this.birthday = user.getUserDetail().getBirthday();
			this.phone = user.getUserDetail().getPhone();
			this.city = user.getUserDetail().getCity();
		}
		this.userEmail=user.getUserEmail();
		this.googletoken=user.getGoogleSubId();
	}

	public Integer getUsersId() {
		return usersId;
	}

	public void setUsersId(Integer usersId) {
		this.usersId = usersId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Long getBirthday() {
		return birthday;
	}

	public void setBirthday(Long birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGoogletoken() {
		return googletoken;
	}

	public void setGoogletoken(String googletoken) {
		this.googletoken = googletoken;
	}
	
}
