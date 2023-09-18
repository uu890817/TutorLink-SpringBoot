package tw.tutorlink.bean;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Vacation")
public class Vacation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VacationId")
	private Integer vacationId;

	@ManyToOne
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId", nullable = false)
	private Users users;

	@Column(name = "VacationTime")
	private Date vacationTime;


	@Column(name = "IsRepeat", columnDefinition = "bit")
	private boolean isRepeat;
	
	// 休息日 週一：1;週二：2;週三:3... 使用,分隔 可選擇多日休息時間
    @Column(name = "RepeatDayOfWeek") 
    private String repeatDayOfWeek;

	public Vacation(Users users, Date vacationTime, boolean isRepeat, String repeatDayOfWeek) {
		super();
		this.users = users;
		this.vacationTime = vacationTime;
		this.isRepeat = isRepeat;
		this.repeatDayOfWeek = repeatDayOfWeek;
	}

	public Vacation() {
		super();
	}

	public Integer getVacationId() {
		return vacationId;
	}

	public void setVacationId(Integer vacationId) {
		this.vacationId = vacationId;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Date getVacationTime() {
		return vacationTime;
	}

	public void setVacationTime(Date vacationTime) {
		this.vacationTime = vacationTime;
	}

	public boolean getRepeat() {
		return isRepeat;
	}

	public void setRepeat(boolean isRepeat) {
		this.isRepeat = isRepeat;
	}

	public String getRepeatDayOfWeek() {
		return repeatDayOfWeek;
	}

	public void setRepeatDayOfWeek(String repeatDayOfWeek) {
		this.repeatDayOfWeek = repeatDayOfWeek;
	}
    
    

}
