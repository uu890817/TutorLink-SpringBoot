package tw.tutorlink.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId",nullable = false)
	private Users users;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="LessonDetailId",referencedColumnName = "lessonDetailId")
	private LessonDetail lessonDetail;
	
	@Column(name="Title",columnDefinition = "nvarchar(50)")
	private String title;
	
	@Column(name="PostContent",columnDefinition = "nvarchar(max)")
	private String postContent;

	
	// Getter/Setter-----------------------------------------------
	public Integer getLessonPostId() {
		return lessonPostId;
	}

	public void setLessonPostId(Integer lessonPostId) {
		this.lessonPostId = lessonPostId;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public LessonDetail getLessonDetail() {
		return lessonDetail;
	}

	public void setLessonDetail(LessonDetail lessonDetail) {
		this.lessonDetail = lessonDetail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	
	
}
