package tw.tutorlink.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="VideoNote")
public class VideoNote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="VideoNoteId")
	private Integer videoNoteId;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="VideoId",referencedColumnName = "videoId")
	private Video video;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId",nullable = false)
	private Users users;
	
	@Column(name="TimeLine",columnDefinition = "int")
	private Integer timeLine;
	
	
	@Column(name="NoteContent",columnDefinition = "nvarchar(max)")
	private String noteContent;


	public Integer getVideoNoteId() {
		return videoNoteId;
	}


	public void setVideoNoteId(Integer videoNoteId) {
		this.videoNoteId = videoNoteId;
	}


	public Video getVideo() {
		return this.video;
	}


	public void setVideo(Video video) {
		this.video = video;
	}


	public Users getUsers() {
		return this.users;
	}


	public void setUsers(Users users) {
		this.users = users;
	}


	public Integer getTimeLine() {
		return timeLine;
	}


	public void setTimeLine(Integer timeLine) {
		this.timeLine = timeLine;
	}


	public String getNoteContent() {
		return noteContent;
	}


	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	
}
