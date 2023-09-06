package tw.tutorlink.bean;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ExerciseConfig")
public class ExerciseConfig {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ExerConfigId")
	private Integer exerConfigId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ExerId", referencedColumnName = "exerId")
	private Exercises exercises;

	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ExerPerId", referencedColumnName = "exerPerId")
	private ExercisePermissions exercisePermissions;

	@Column(name = "Type")
	private Integer type;

	@Column(name = "StartTime",columnDefinition = "Datetime2")
	private Date startTime;

	@Column(name = "EndTime",columnDefinition = "Datetime2")
	private Date endTime;

	@Column(name = "TimeCountDown")
	private Integer timeCountDown;

	@Column(name = "FinishShowAnswer")
	private boolean finishShowAnswer;

	
	public Integer getExerConfigId() {
		return exerConfigId;
	}

	public void setExerConfigId(Integer exerConfigId) {
		this.exerConfigId = exerConfigId;
	}

	public Exercises getExercises() {
		return exercises;
	}

	public void setExercises(Exercises exercises) {
		this.exercises = exercises;
	}

	public ExercisePermissions getExercisePermissions() {
		return exercisePermissions;
	}

	public void setExercisePermissions(ExercisePermissions exercisePermissions) {
		this.exercisePermissions = exercisePermissions;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getTimeCountDown() {
		return timeCountDown;
	}

	public void setTimeCountDown(Integer timeCountDown) {
		this.timeCountDown = timeCountDown;
	}

	public boolean isFinishShowAnswer() {
		return finishShowAnswer;
	}

	public void setFinishShowAnswer(boolean finishShowAnswer) {
		this.finishShowAnswer = finishShowAnswer;
	}

}
