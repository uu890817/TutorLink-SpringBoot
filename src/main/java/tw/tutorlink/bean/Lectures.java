package tw.tutorlink.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Lectures")
public class Lectures {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LectureId")
	private Integer lectureId;

	@ManyToOne
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId", nullable = false)
	private Users users;

	@Column(name = "LectureName",columnDefinition = "nvarchar(50)")
	private String lectureName;

	@Column(name = "LeactureType",columnDefinition = "bit")
	private boolean leactureType;

	@Column(name = "LeacturePrice")
	private Integer leacturePrice;

}
