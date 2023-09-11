package tw.tutorlink.dto.exercises;

import java.util.ArrayList;
import java.util.List;

import tw.tutorlink.bean.ExerciseConfig;
import tw.tutorlink.bean.ExercisePermissions;
import tw.tutorlink.bean.Exercises;
import tw.tutorlink.bean.Options;
import tw.tutorlink.bean.StudentAnswers;
import tw.tutorlink.bean.Topics;

public class StudentGetExerciseDTO {
	private Integer exerId;
	private String exerName;
	private List<Topics> topics;
	private ExerciseConfig exerciseConfig;
	
	private Integer score;
	private Integer overwriteScore;
	private List<StudentAnswers> studentAnswers;
	
	
	public StudentGetExerciseDTO() {
	}

	public StudentGetExerciseDTO(ExercisePermissions ep) {
		Exercises e = ep.getExercises();
		this.exerId = e.getExerId();
		this.exerName = e.getExerName();
		
		List<Topics> ts = e.getTopics();
		List<Topics> newT = new ArrayList<>();
		for(Topics t: ts) {
			List<Options> os = t.getOptions();
			List<Options> newO = new ArrayList<>();
			for(Options o: os) {
				o.setAnswer("自己想( OAO)/");
				newO.add(o);
			}
			t.setOptions(newO);
			newT.add(t);
		}
		this.topics = newT;
		
		this.exerciseConfig = ep.getExerciseConfig();
		
		this.score = ep.getScore();
		this.overwriteScore = ep.getOverwriteScore();
		this.studentAnswers = ep.getStudentAnswers();
	}
	
	
	
	
	
	
	public Integer getExerId() {
		return exerId;
	}
	public void setExerId(Integer exerId) {
		this.exerId = exerId;
	}
	public String getExerName() {
		return exerName;
	}
	public void setExerName(String exerName) {
		this.exerName = exerName;
	}
	public List<Topics> getTopics() {
		return topics;
	}
	public void setTopics(List<Topics> topics) {
		this.topics = topics;
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
