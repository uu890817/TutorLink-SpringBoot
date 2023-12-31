package tw.tutorlink.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Topics")
public class Topics {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TopicsId")
	private Integer topicsId;
	
	@ManyToOne
	@JsonIgnore()
	@JoinColumn(name="ExerId",referencedColumnName = "exerId")
	private Exercises exercises;
	
	@Column(name="Content",columnDefinition = "NVARCHAR(500)",nullable = false)
	private String content;
	
	@Column(name="Type",nullable = false)
	private Integer type;
	
	@Column(name="SortId")
	private Integer sortId;
	
	// 關聯性欄位-----------------------------------------------------
	@JsonIgnoreProperties("topics")
	@OneToMany(mappedBy = "topics",cascade = CascadeType.ALL)
	private List<Options> options;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "topics")
	private  List<StudentAnswers> studentAnswers;

	public Integer getTopicsId() {
		return topicsId;
	}

	public void setTopicsId(Integer topicsId) {
		this.topicsId = topicsId;
	}

	public Exercises getExercises() {
		return exercises;
	}

	public void setExercises(Exercises exercises) {
		this.exercises = exercises;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<Options> getOptions() {
		return options;
	}

	public void setOptions(List<Options> options) {
		this.options = options;
	}

	public Integer getSortId() {
		return sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

	public List<StudentAnswers> getStudentAnswers() {
		return studentAnswers;
	}

	public void setStudentAnswers(List<StudentAnswers> studentAnswers) {
		this.studentAnswers = studentAnswers;
	}


	
	
	
	
	
}
