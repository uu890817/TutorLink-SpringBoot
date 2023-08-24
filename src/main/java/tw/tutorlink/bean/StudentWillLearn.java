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
@Table(name="StudentWillLearn")
public class StudentWillLearn {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="WillLearnId")
	private Integer willLearnId;
	
	@ManyToOne
	@JoinColumn(name="LessonId",referencedColumnName = "lessonId")
	private Lessons lesson;
	
	@Column(name="WillLearnContent")
	private String willLearnContent;

}
