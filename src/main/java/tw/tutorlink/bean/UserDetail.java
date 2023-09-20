package tw.tutorlink.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "UserDetail")
public class UserDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserDetailId")
	private Integer userDetailId;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId")
	private Users users;

	@Column(name = "UserName",columnDefinition = "nvarchar(50)")
	private String userName;

	@Column(name = "Gender")
	private Integer gender;

	@Column(name = "Phone")
	private String phone;
	
	@Column(name = "Image",columnDefinition = "varchar(100)")
	private String image;
	
	@Column(name = "ImageByte",columnDefinition = "varbinary(MAX)")
	private byte[] imageByte;

	@Column(name = "Birthday")
	private long birthday;

	public byte[] getImageByte() {
		return imageByte;
	}

	public void setImageByte(byte[] imageByte) {
		this.imageByte = imageByte;
	}

	@Column(name = "City",columnDefinition = "nvarchar(50)")
	private String city;

	@Column(name = "CreateDate")
	private Date createDate;
	
	@Column(name="IdCard",columnDefinition = "nvarchar(50)")
	private String idCard;

	@Column(name = "TeacherState")
	private Integer  teacherState;
	// 是否為老師的狀態，是、不是、審核中

	@Column(name = "LastLoginTime")
	private Date lastLoginTime;
	
	@Column(name = "NewLoginTime")
	private Date newLoginTime;

	public Integer getUserDetailId() {
		return userDetailId;
	}

	public void setUserDetailId(Integer userDetailId) {
		this.userDetailId = userDetailId;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public long getBirthday() {
		return birthday;
	}

	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Integer getTeacherState() {
		return teacherState;
	}

	public void setTeacherState(Integer teacherState) {
		this.teacherState = teacherState;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getNewLoginTime() {
		return newLoginTime;
	}

	public void setNewLoginTime(Date newLoginTime) {
		this.newLoginTime = newLoginTime;
	}

}
