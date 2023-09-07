package tw.tutorlink.dto.exercises;

import java.util.List;

import tw.tutorlink.bean.ExerciseConfig;
import tw.tutorlink.bean.Exercises;
import tw.tutorlink.bean.Topics;

public class TeacherGetExerciseInfoDTO {

	
	private Integer exerId;
	private Integer lessonId;
	private String exerName;
	private List<Topics> topics;
	private ExerciseConfig exerciseConfig;
	
	public TeacherGetExerciseInfoDTO(Exercises exercise) {
		this.exerId = exercise.getExerId();
		this.lessonId = exercise.getLesson().getLessonId();
		this.exerName = exercise.getExerName();
		this.topics = exercise.getTopics();
		this.exerciseConfig = exercise.getExerciseConfig();
	}

	public Integer getExerId() {
		return exerId;
	}

	public void setExerId(Integer exerId) {
		this.exerId = exerId;
	}

	public Integer getLessonId() {
		return lessonId;
	}

	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
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

	
	
	
	
	
	
	
	
	
	
}
