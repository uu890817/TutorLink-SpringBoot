package tw.tutorlink.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	@JsonIgnoreProperties({ "subject", "lessonType", "price", "image", "lessondetail", "order",
		"shoppingCart", "report", "favorite", "exercises", "calender", "studentWillLearn", "courseQA" })
	private Lessons lesson;

	@ManyToOne
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId", nullable = false)
	@JsonIgnoreProperties({ "userPassword", "userEmail", "userType", "googleSubId", "applyTeacher", "lesson", "order",
		"comment", "Cart", "report", "favorite", "exercises", "calender", "vacation", "question", "lessonPost",
		"videoNote", "courseQA", "cart","exercisePermissions","userDetail" })
	private Users users;

	@Column(name = "CreateTime", nullable = false)
	private Date createTime;

	@Column(name = "ReportType", columnDefinition = "nvarchar(50)")
	private String reportType;

	@Column(name = "ReportContent", columnDefinition = "nvarchar(500)")
	private String reportContent;

	// 0:未處理 1:處理中 2:已處理
	@Column(name = "Status")
	private Integer status;
	
	// 處理時間
	@Column(name = "ProcessingDate", columnDefinition = "Date")
	private Date processingDate;

	
	
	public Report() {
	}
	
	

	public Report(Lessons lesson, Users users, Date createTime, String reportType, String reportContent, Integer status,
			Date processingDate) {
		super();
		this.lesson = lesson;
		this.users = users;
		this.createTime = createTime;
		this.reportType = reportType;
		this.reportContent = reportContent;
		this.status = status;
		this.processingDate = processingDate;
	}



	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public Lessons getLesson() {
		return lesson;
	}

	public void setLesson(Lessons lesson) {
		this.lesson = lesson;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getProcessingDate() {
		return processingDate;
	}

	public void setProcessingDate(Date processingDate) {
		this.processingDate = processingDate;
	}
	
	
}
