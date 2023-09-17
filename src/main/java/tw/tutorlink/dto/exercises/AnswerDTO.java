package tw.tutorlink.dto.exercises;

import java.util.Date;

import tw.tutorlink.bean.Answer;

public class AnswerDTO {

	private Integer answerId;
	private Integer questionId;
	private Integer usersId;
	private String userName;
	private String content;
	private Date createDate;
	private boolean isDelete;
	private boolean isEdit;
	private boolean isMyQuestion;

	
	public AnswerDTO() {
	}

	public AnswerDTO(Answer a) {
		this.answerId = a.getAnswerId();
		this.questionId = a.getQuestion().getQuestionId();
		this.usersId = a.getUsers().getUsersId();
		this.userName = a.getUsers().getUserDetailUserName();
		this.content = a.getContent();
		this.createDate = a.getCreateDate();
		this.isDelete = a.isDelete();
		this.isEdit = a.isEdit();
	}
	
	public Integer getAnswerId() {
		return answerId;
	}
	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
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
	public boolean isMyQuestion() {
		return isMyQuestion;
	}
	public void setMyQuestion(boolean isMyQuestion) {
		this.isMyQuestion = isMyQuestion;
	}

	
	
	
	
	
	
	
	
	
	
}
