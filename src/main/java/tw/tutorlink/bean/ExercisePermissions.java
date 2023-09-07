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
@Table(name="ExercisePermissions")
public class ExercisePermissions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ExerPerId")
	private Integer exerPerId;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "ExerId", referencedColumnName = "exerId")
	private Exercises exercises;
	
	@ManyToOne
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId", nullable = false)
	private Users users;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ExerPerId",referencedColumnName = "exerPerId")
	private ExerciseConfig exerciseConfig;
	
	@Column(name="Score")
	private Integer score;
	
	@Column(name="OverwriteScore")
	private Integer overwriteScore;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "exercisePermissions")
	private List<StudentAnswers> studentAnswers;
}
