package tw.tutorlink.dto.exercises;

import tw.tutorlink.bean.ExercisePermissions;
import tw.tutorlink.bean.OrderItem;

public class TeacherGetLessonStudentDTO {

	private Integer usersId;
	private String userName;
	private Integer exerPerId;
//	private ExerciseConfig exerConfig;
	private ExercisePermissions exerPermissions;
	
	public TeacherGetLessonStudentDTO() {
	}
	
	public TeacherGetLessonStudentDTO(OrderItem order, ExercisePermissions exercisePermissions) {
		this.usersId = order.getUsers().getUsersId();
		if(order.getUsers().getUserDetail() != null) {
			this.userName = order.getUsers().getUserDetail().getUserName();
		}
		if(exercisePermissions != null) {
			this.exerPermissions = exercisePermissions;
		}

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

	
	
	
	
	
	
	
	
	
	
}
