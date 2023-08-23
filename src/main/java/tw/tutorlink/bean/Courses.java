package tw.tutorlink.bean;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Courses")
public class Courses {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CouresId")
	private Integer courseId;
	
	@Column(name="CreateTime")
	private Date createTime;
	
	@Column(name="CourseUrl")
	private String courseUrl;
	
	@Column(name="CourseTotalTime")
	private Integer courseTotalTime;

	@Column(name="Infomation",columnDefinition = "nvarchar(max)")
	private String infomation;

}
