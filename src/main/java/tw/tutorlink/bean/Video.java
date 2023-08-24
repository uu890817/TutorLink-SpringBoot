package tw.tutorlink.bean;

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
@Table(name="Video")
public class Video {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="VideoId")
	private Integer VideoId;
	
	@ManyToOne
	@JoinColumn(name="LessonDetailId",referencedColumnName = "lessonDetailId")
	private LessonDetail lessonDetail;
	
	@Column(name="Sort")
	private Integer sort;
	
	@Column(name="ChapterName",columnDefinition = "nvarchar(50)")
	private String chapterName;
	
	@Column(name="CoureseUrl",columnDefinition = "varchar(100)")
	private String courseUrl;
	
	// 關聯性欄位-----------------------------------------------------
	
	@OneToOne(mappedBy = "video",cascade = CascadeType.ALL)
	private VideoNote videoNote;
	
	

}
