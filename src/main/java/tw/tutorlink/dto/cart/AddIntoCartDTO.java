package tw.tutorlink.dto.cart;

import java.util.Date;
import java.util.List;



public class AddIntoCartDTO {
	
	private List<Long> selectedTimes;
	
	private Integer lessonsId;
	
	private Date addTime;


	public AddIntoCartDTO() {
	
	}

	public Integer getLessonsId() {
		return lessonsId;
	}

	public void setLessonsId(Integer lessonsId) {
		this.lessonsId = lessonsId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public List<Long> getSelectedTimes() {
		return selectedTimes;
	}

	public void setSelectedTimes(List<Long> selectedTimes) {
		this.selectedTimes = selectedTimes;
	}



	
}
