package tw.tutorlink.dto.exercises;

import tw.tutorlink.bean.CartItem;
import tw.tutorlink.bean.ExerciseConfig;
import tw.tutorlink.bean.ExercisePermissions;

public class TeacherGetLessonStudentDTO {

	private Integer usersId;
	private String userName;
	private Integer exerPerId;
	private ExerciseConfig exerConfig;
	private ExercisePermissions exerPermissions;
	
	public TeacherGetLessonStudentDTO() {
	}
	
	public TeacherGetLessonStudentDTO(CartItem cartItem, ExercisePermissions exercisePermissions) {
		
	}

	public Integer getUsersId() {
		return usersId;
	}

	public void setUsersId(Integer usersId) {
		this.usersId = usersId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ExercisePermissions getExerPermissions() {
		return exerPermissions;
	}

	public void setExerPermissions(ExercisePermissions exerPermissions) {
		this.exerPermissions = exerPermissions;
	}

	public Integer getExerPerId() {
		return exerPerId;
	}

	public void setExerPerId(Integer exerPerId) {
		this.exerPerId = exerPerId;
	}

	
	public ExerciseConfig getExerConfig() {
		return exerConfig;
	}

	public void setExerConfig(ExerciseConfig exerConfig) {
		this.exerConfig = exerConfig;
	}

	
	
	
	
	
	
	
	
}
