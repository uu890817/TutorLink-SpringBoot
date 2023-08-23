package tw.tutorlink.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="StudentAnswers")
public class StudentAnswers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="StudentAnsId")
	private Integer studentAnsId;
	
//	@ManyToOne
//	@JoinColumn(name="ExerPerId",referencedColumnName = "exerPerId")
//	private ExercisePermissions exercisePermissions;
//	
//	@OneToOne
//	@JoinColumn(name="TopicsId",referencedColumnName = "topicsId")
//	private Topics topics;
//	
	@Column(name="Answer")
	private String answer;
}
