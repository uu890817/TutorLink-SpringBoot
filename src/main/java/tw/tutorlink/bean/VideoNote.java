package tw.tutorlink.bean;

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
	
	@OneToOne
	@JoinColumn(name="VideoId",referencedColumnName = "VideoId")
	private Video video;
	
	@ManyToOne
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId",nullable = false)
	private Users users;
	
	@Column(name="TimeLine",columnDefinition = "int")
	private Integer timeLine;
	
	
	@Column(name="NoteContent",columnDefinition = "nvarchar(max)")
	private String noteContent;

}
