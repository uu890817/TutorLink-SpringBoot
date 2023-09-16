package tw.tutorlink.dto.exercises;

import java.util.Date;

import tw.tutorlink.bean.Exercises;
import tw.tutorlink.bean.Question;
import tw.tutorlink.bean.Users;

public class QuestionDTO {

	private Integer questionId;
	private Integer exerId;
	private Integer usersId;
	private String content;
	private Date createDate;
	private boolean isDelete;
	private boolean isEdit;

	public QuestionDTO() {

	}

	public QuestionDTO(Question q) {
		this.exerId = q.getExercises().getExerId();
		this.usersId = q.getUsers().getUsersId();
		this.content = q.getContent();
		this.createDate = q.getCreateDate();
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getExerId() {
		return exerId;
	}

	public void setExerId(Integer exerId) {
		this.exerId = exerId;
	}

	public Integer getUsersId() {
		return usersId;
	}

	public void setUsersId(Integer usersId) {
		this.usersId = usersId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public boolean isEdit() {
		return isEdit;
	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}

	

}
