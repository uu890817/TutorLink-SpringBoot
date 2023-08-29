<<<<<<< HEAD
package tw.tutorlink.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore
	@OneToMany(mappedBy = "subject",cascade = CascadeType.ALL)
	private List<Lessons> lesson;

	// Getter/Setter-----------------------------------------------
	
	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectContent() {
		return subjectContent;
	}

	public void setSubjectContent(String subjectContent) {
		this.subjectContent = subjectContent;
	}

	public List<Lessons> getLesson() {
		return lesson;
	}

	public void setLesson(List<Lessons> lesson) {
		this.lesson = lesson;
	}
	
	
	

}
=======
//package tw.tutorlink.bean;
//
//import java.util.List;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name="Subject")
//public class Subject {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name="SubjectId")
//	private Integer subjectId;
//	
//	@Column(name="SubjectContent",columnDefinition = "nvarchar(50)")
//	private String subjectContent;
//	
//	// 關聯性欄位-----------------------------------------------------
//	
//	@OneToMany(mappedBy = "subject",cascade = CascadeType.ALL)
//	private List<Lessons> lesson;
//
//	// Getter/Setter-----------------------------------------------
//	
//	public Integer getSubjectId() {
//		return subjectId;
//	}
//
//	public void setSubjectId(Integer subjectId) {
//		this.subjectId = subjectId;
//	}
//
//	public String getSubjectContent() {
//		return subjectContent;
//	}
//
//	public void setSubjectContent(String subjectContent) {
//		this.subjectContent = subjectContent;
//	}
//
//	public List<Lessons> getLesson() {
//		return lesson;
//	}
//
//	public void setLesson(List<Lessons> lesson) {
//		this.lesson = lesson;
//	}
//	
//	
//	
//
//}
>>>>>>> branch 'develop' of https://github.com/uu890817/TutorLink-SpringBoot.git
