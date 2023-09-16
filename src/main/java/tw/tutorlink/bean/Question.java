package tw.tutorlink.bean;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Question")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "QuestionId")
	private Integer questionId;

	@ManyToOne
	@JoinColumn(name = "ExerId", referencedColumnName = "exerId")
	private Exercises exercises;

	@ManyToOne
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId", nullable = false)
	private Users users;

	@Column(name = "Content", columnDefinition = "nvarchar(100)")
	private String content;

	@Column(name = "CreateDate", columnDefinition = "datetime2")
	private Date createDate;

	@Column(name = "IsDelete", columnDefinition = "bit")
	private boolean isDelete;

	@Column(name = "IsEdit", columnDefinition = "bit")
	private boolean isEdit;
	
	// 關聯性欄位-----------------------------------------------------

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
	private List<Answer> answer;
}
