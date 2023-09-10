package tw.tutorlink.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore
	@JoinColumn(name="LessonDetailId",referencedColumnName = "lessonDetailId")
	private LessonDetail lessonDetail;
	
	@Column(name="Sort")
	private Integer sort;
	
	@Column(name="ChapterName",columnDefinition = "nvarchar(50)")
	private String chapterName;
	
	@Column(name="CoureseUrl",columnDefinition = "varchar(100)")
	private String courseUrl;
	
	// 關聯性欄位-----------------------------------------------------
	
	@OneToMany(mappedBy = "video",cascade = CascadeType.ALL)
	private List<VideoNote> videoNote;

	
	public Integer getVideoId() {
		return VideoId;
	}

	public void setVideoId(Integer videoId) {
		VideoId = videoId;
	}

	public LessonDetail getLessonDetail() {
		return lessonDetail;
	}

	public void setLessonDetail(LessonDetail lessonDetail) {
		this.lessonDetail = lessonDetail;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public String getCourseUrl() {
		return courseUrl;
	}

	public void setCourseUrl(String courseUrl) {
		this.courseUrl = courseUrl;
	}

	public List<VideoNote> getVideoNote() {
		return videoNote;
	}

	public void setVideoNote(List<VideoNote> videoNote) {
		this.videoNote = videoNote;
	}

	
	
	
	

}
