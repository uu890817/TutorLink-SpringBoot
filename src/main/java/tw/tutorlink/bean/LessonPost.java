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
@Table(name="LessonPost")
public class LessonPost {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="LessonPostId")
	private Integer lessonPostId;
	
	@ManyToOne
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId",nullable = false)
	private Users users;

	@ManyToOne
	@JoinColumn(name="LessonDetailId",referencedColumnName = "lessonDetailId")
	private LessonDetail lessonDetail;
	
	@Column(name="Title",columnDefinition = "nvarchar(50)")
	private String title;
	
	@Column(name="PostContent",columnDefinition = "nvarchar(max)")
	private String postContent;

}
