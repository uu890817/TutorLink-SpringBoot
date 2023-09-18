package tw.tutorlink.dto.exercises;

import java.util.Date;
import java.util.List;

import tw.tutorlink.bean.Question;

public class QuestionDTO {

	private Integer questionId;
	private Integer exerId;
	private Integer usersId;
	private String userName;
	private String content;
	private Date createDate;
	private boolean isDelete;
	private boolean isEdit;
	private boolean isMyQuestion;
	private List<AnswerDTO> answer;
	public QuestionDTO() {

	}

	public QuestionDTO(Question q) {
		if(q.getQuestionId() != null) {
			this.questionId = q.getQuestionId();
		}

		this.exerId = q.getExercises().getExerId();
		
		if(q.getUsers() != null) {
			this.usersId = q.getUsers().getUsersId();
			if(q.getUsers().getUserDetail() != null) {
				this.userName = q.getUsers().getUserDetail().getUserName();
			}
		}
		
		this.content = q.getContent();
		this.createDate = q.getCreateDate();
		
		this.isDelete = q.isDelete();
		this.isEdit = q.isEdit();
		
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isMyQuestion() {
		return isMyQuestion;
	}

	public void setMyQuestion(boolean isMyQuestion) {
		this.isMyQuestion = isMyQuestion;
	}

	public List<AnswerDTO> getAnswer() {
		return answer;
	}

	public void setAnswer(List<AnswerDTO> answer) {
		this.answer = answer;
	}


	

}
