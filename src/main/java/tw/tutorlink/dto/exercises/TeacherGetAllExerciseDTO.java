package tw.tutorlink.dto.exercises;

import tw.tutorlink.bean.ExerciseConfig;
import tw.tutorlink.bean.Exercises;

public class TeacherGetAllExerciseDTO {

	private Integer exerId;
	private String lessonName;
	private String exerName;
	private Integer exerType;
//	private Exercises e;

	public TeacherGetAllExerciseDTO() {

	}

	public TeacherGetAllExerciseDTO(Exercises exercises) {
		this.exerId = exercises.getExerId();
		if (exercises.getLesson() != null) {
			this.lessonName = exercises.getLesson().getLessonName();
		}

		this.exerName = exercises.getExerName();
		if (exercises.getExerciseConfig() != null) {
			this.exerType = exercises.getExerciseConfig().getType();
		}
		
//		this.e = exercises;
	}

	public Integer getExerType() {
		return exerType;
	}
//
//	public Exercises getE() {
//		return e;
//	}
//
//	public void setE(Exercises e) {
//		this.e = e;
//	}

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
}
