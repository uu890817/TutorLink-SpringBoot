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

	@Column(name = "StartTime", columnDefinition = "Date")
	private Date startTime;

	@Column(name = "EndTime", columnDefinition = "Date")
	private Date endTime;

	@Column(name = "IsRepeat", columnDefinition = "bit")
	private boolean isRepeat;

}
