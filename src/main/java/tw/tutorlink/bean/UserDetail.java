package tw.tutorlink.bean;

import java.util.Date;

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
	@Column(name = "UserDtId")
	private Integer userDtId;

	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId")
	private Users users;

	@Column(name = "UserName", nullable = false,columnDefinition = "nvarchar(50)")
	private String userName;

	@Column(name = "Gender", nullable = false)
	private Integer gender;

	@Column(name = "Image",columnDefinition = "varchar(100)")
	private String image;

	@Column(name = "Birthday")
	private Date birthday;

	@Column(name = "City",columnDefinition = "nvarchar(50)")
	private String City;

	@Column(name = "CreateDate")
	private Date createDate;

	@Column(name = "TeacherState",columnDefinition = "bit")
	private boolean teacherState;
	// 是否為老師的狀態，是、不是、審核中

	@Column(name = "LastLoginTime")
	private Date lastLoginTime;

}
