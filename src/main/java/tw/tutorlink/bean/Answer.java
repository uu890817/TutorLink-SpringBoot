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
@Table(name = "Answer")
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AnswerId")
	private Integer answerId;
	
	@ManyToOne
	@JoinColumn(name="QuestionId",referencedColumnName = "questionId")
	private Question question;

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
	
}
