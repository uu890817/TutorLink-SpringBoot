package tw.tutorlink.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ApplyTeacher")
public class ApplyTeacher {
	
	@Id
	@Column(name="ApplyTeacherId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer applyTeacherId;

	@OneToOne
	@JoinColumn(name="UsersId",referencedColumnName = "usersId")
	private Users users;
	
	@Column(name="IdNumber")
	private String idNumber;
	
	@Column(name="Country")
	private String country;
	
	@Column(name="Mainlessons")
	private String mainlessons ;
	
	@Column(name="Langs")
	private String langs ;
	
	@Column(name="Exp")
	private String exp;
	
	@Column(name="Jobstate")
	private String jobstate;
	
	@Column(name="Hours")
	private String hours;
	
	@Column(name="Salary")
	private String salary;
	
	@Column(name="Advantage")
	private String advantage;
	
	@Column(name="State")
	private String state;

	public Integer getApplyTeacherId() {
		return applyTeacherId;
	}

	public void setApplyTeacherId(Integer applyTeacherId) {
		this.applyTeacherId = applyTeacherId;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMainlessons() {
		return mainlessons;
	}

	public void setMainlessons(String mainlessons) {
		this.mainlessons = mainlessons;
	}

	public String getLangs() {
		return langs;
	}

	public void setLangs(String langs) {
		this.langs = langs;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public String getJobstate() {
		return jobstate;
	}

	public void setJobstate(String jobstate) {
		this.jobstate = jobstate;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getAdvantage() {
		return advantage;
	}

	public void setAdvantage(String advantage) {
		this.advantage = advantage;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


}

