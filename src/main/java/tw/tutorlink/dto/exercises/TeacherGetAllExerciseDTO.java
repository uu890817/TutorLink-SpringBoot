package tw.tutorlink.dto.exercises;

import tw.tutorlink.bean.Exercises;

public class TeacherGetAllExerciseDTO {

	private Integer exerId;
	private String lessonName;
	private Integer lessonId;
	private String exerName;
	private Integer exerType;
//	private Exercises e;

	public TeacherGetAllExerciseDTO() {

	}

	public TeacherGetAllExerciseDTO(Exercises exercises) {
		this.exerId = exercises.getExerId();
		if (exercises.getLesson() != null) {
			this.lessonName = exercises.getLesson().getLessonName();
			this.lessonId = exercises.getLesson().getLessonId();
		}

		this.exerName = exercises.getExerName();
		if (exercises.getExerciseConfig() != null) {
			this.exerType = exercises.getExerciseConfig().getType();
		}
		
	}

	public Integer getExerType() {
		return exerType;
	}


	public void setExerType(Integer exerType) {
		this.exerType = exerType;
	}

	public Integer getExerId() {
		return exerId;
	}

	public void setExerId(Integer exerId) {
		this.exerId = exerId;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public String getExerName() {
		return exerName;
	}

	public void setExerName(String exerName) {
		this.exerName = exerName;
	}

	public Integer getLessonId() {
		return lessonId;
	}

	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}
	
}
