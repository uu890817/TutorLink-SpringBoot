package tw.tutorlink.dto.exercises;

import tw.tutorlink.bean.ExerciseConfig;
import tw.tutorlink.bean.ExercisePermissions;

public class StudentGetAllExerciseDTO {

	private Integer exerPerId;
	
	private Integer exerId;
	private String exerName;
	
	private String subjectContent;
	private String lessonName;
	
	private ExerciseConfig exerciseConfig;
	
	private Integer score;
	private Integer overwriteScore;


	public StudentGetAllExerciseDTO() {

	}

	public StudentGetAllExerciseDTO(ExercisePermissions ep) {
		this.exerPerId = ep.getExerPerId();
		
		this.exerId = ep.getExercises().getExerId();
		this.exerName = ep.getExercises().getExerName();
		
		this.subjectContent = ep.getExercises().getLesson().getSubject().getSubjectContent();
		this.lessonName = ep.getExercises().getLesson().getLessonName();
		
		ExerciseConfig ec = ep.getExerciseConfig();
		ec.setExercisePermissions(null);
		this.exerciseConfig = ec;
		
		this.score = ep.getScore();
		this.overwriteScore = ep.getOverwriteScore();
	}

	
	public Integer getExerPerId() {
		return exerPerId;
	}

	public void setExerPerId(Integer exerPerId) {
		this.exerPerId = exerPerId;
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

	public String getSubjectContent() {
		return subjectContent;
	}

	public void setSubjectContent(String subjectContent) {
		this.subjectContent = subjectContent;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
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

	
	
	
	
	
	
}
