package tw.tutorlink.bean;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Subject")
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SubjectId")
	private Integer subjectId;
	
	@Column(name="SubjectContent",columnDefinition = "nvarchar(50)")
	private String subjectContent;
	
	// 關聯性欄位-----------------------------------------------------
	
	@OneToMany(mappedBy = "subject",cascade = CascadeType.ALL)
	private List<Lessons> lesson;

}
