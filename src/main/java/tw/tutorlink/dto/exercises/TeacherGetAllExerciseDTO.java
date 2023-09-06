package tw.tutorlink.dto.exercises;

import tw.tutorlink.bean.Exercises;

public class TeacherGetAllExerciseDTO {

	private Integer exerId;
	private String lessonName;
	private String exerName;

	public TeacherGetAllExerciseDTO() {
		
	}

	public TeacherGetAllExerciseDTO(Exercises exercises) {
		this.exerId = exercises.getExerId();
		this.lessonName = exercises.getLesson().getLessonName();
		this.exerName = exercises.getExerName();

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
}
