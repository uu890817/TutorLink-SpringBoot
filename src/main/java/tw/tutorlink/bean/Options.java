package tw.tutorlink.bean;

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
@Table(name = "Options")
public class Options {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="OptionsId")
	private Integer optionsId;

	@ManyToOne
	@JoinColumn(name="TopicsId",referencedColumnName =  "topicsId")
	private Topics topics;
	
	@Column(name="Content",columnDefinition = "nvarchar(100)")
	private String content;
	
	@Column(name="SortId")
	private Integer sortId;
	
	public Integer getSortId() {
		return sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

	@Column(name="Answer",columnDefinition = "nvarchar(100)")
	private String answer;

	public Integer getOptionsId() {
		return optionsId;
	}

	public void setOptionsId(Integer optionsId) {
		this.optionsId = optionsId;
	}

	public Topics getTopics() {
		return topics;
	}

	public void setTopics(Topics topics) {
		this.topics = topics;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
