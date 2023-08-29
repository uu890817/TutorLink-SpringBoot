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
	
	@Column(name="IdCard")
	private String idCard;
	
	@Column(name = "IdCardImageFont",columnDefinition = "varchar(100)")
	private String idCardImageFont;
	
	@Column(name = "IdCardImageBack",columnDefinition = "varchar(100)")
	private String idCardImageBack;
	
}
