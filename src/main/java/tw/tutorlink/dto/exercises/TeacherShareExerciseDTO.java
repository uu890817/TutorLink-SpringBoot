package tw.tutorlink.dto.exercises;

import tw.tutorlink.bean.ExerciseConfig;
import tw.tutorlink.bean.ExercisePermissions;
import tw.tutorlink.bean.Exercises;
import tw.tutorlink.bean.Users;

public class TeacherShareExerciseDTO {

	private Integer exerPerId;
	private Exercises exercises;
	private Users users;
	private ExerciseConfig exerciseConfig;
	
	public TeacherShareExerciseDTO() {
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
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public ExerciseConfig getExerciseConfig() {
		return exerciseConfig;
	}
	public void setExerciseConfig(ExerciseConfig exerciseConfig) {
		this.exerciseConfig = exerciseConfig;
	}


	
}

