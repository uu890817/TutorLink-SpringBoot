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
@Table(name = "Report")
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ReportId")
	private Integer reportId;

	@ManyToOne
	@JoinColumn(name = "LessonId", referencedColumnName = "lessonId", nullable = false)
	private Lessons lesson;

	@ManyToOne
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId", nullable = false)
	private Users users;

	@Column(name = "CreateTime", nullable = false)
	private Date createTime;

	@Column(name = "ReportType", columnDefinition = "nvarchar(50)")
	private String reportType;

	@Column(name = "ReportContent", columnDefinition = "nvarchar(500)")
	private String reportContent;

	@Column(name = "Status")
	private Integer status;

	@Column(name = "ProcessingDate", columnDefinition = "Date")
	private Date processingDate;
}
