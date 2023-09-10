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
@Table(name="StudentWillLearn")
public class StudentWillLearn {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="WillLearnId")
	private Integer willLearnId;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="LessonId",referencedColumnName = "lessonId")
	private Lessons lesson;
	
	@Column(name="WillLearnContent")
	private String willLearnContent;

	public Integer getWillLearnId() {
		return willLearnId;
	}

	public void setWillLearnId(Integer willLearnId) {
		this.willLearnId = willLearnId;
	}

	public Lessons getLesson() {
		return lesson;
	}

	public void setLesson(Lessons lesson) {
		this.lesson = lesson;
	}

	public String getWillLearnContent() {
		return willLearnContent;
	}

	public void setWillLearnContent(String willLearnContent) {
		this.willLearnContent = willLearnContent;
	}

}
