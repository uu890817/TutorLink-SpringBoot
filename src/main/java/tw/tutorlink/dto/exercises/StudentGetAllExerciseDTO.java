package tw.tutorlink.dto.exercises;

import java.util.List;

import tw.tutorlink.bean.ExerciseConfig;
import tw.tutorlink.bean.ExercisePermissions;
import tw.tutorlink.bean.Exercises;
import tw.tutorlink.bean.StudentAnswers;

public class StudentGetAllExerciseDTO {

	private Integer exerPerId;
	private Exercises exercises;
	private ExerciseConfig exerciseConfig;
	private Integer score;
	private Integer overwriteScore;
	private List<StudentAnswers> studentAnswers;

	public StudentGetAllExerciseDTO() {

	}

	public StudentGetAllExerciseDTO(ExercisePermissions ep) {
		this.exerPerId = ep.getExerPerId();
		this.exercises = ep.getExercises();
		this.exerciseConfig = ep.getExerciseConfig();
		this.score = ep.getScore();
		this.overwriteScore = ep.getOverwriteScore();
		
	}

	public Integer getExerPerId() {
		return exerPerId;
	}

	public void setExerPerId(Integer exerPerId) {
		this.exerPerId = exerPerId;
	}

	public Exercises getExercises() {
		return exercises;
	}

	public void setExercises(Exercises exercises) {
		this.exercises = exercises;
	}

	public ExerciseConfig getExerciseConfig() {
		return exerciseConfig;
	}

	public void setExerciseConfig(ExerciseConfig exerciseConfig) {
		this.exerciseConfig = exerciseConfig;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getOverwriteScore() {
		return overwriteScore;
	}

	public void setOverwriteScore(Integer overwriteScore) {
		this.overwriteScore = overwriteScore;
	}

	public List<StudentAnswers> getStudentAnswers() {
		return studentAnswers;
	}

	public void setStudentAnswers(List<StudentAnswers> studentAnswers) {
		this.studentAnswers = studentAnswers;
	}

	
	
	
}
