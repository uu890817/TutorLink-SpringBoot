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
	
	@Column(name="Answer",columnDefinition = "nvarchar(100)")
	private String answer;
}
