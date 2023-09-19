package tw.tutorlink.dto.cart;

public class SubjectRevenueDTO {
	private Integer videos;
	private Integer lessons;
	private String subject;
	
	public SubjectRevenueDTO(Integer videos, Integer lessons, String subject) {
		super();
		this.videos = videos;
		this.lessons = lessons;
		this.subject = subject;
	}

	public Integer getVideos() {
		return videos;
	}

	public void setVideos(Integer videos) {
		this.videos = videos;
	}

	public Integer getLessons() {
		return lessons;
	}

	public void setLessons(Integer lessons) {
		this.lessons = lessons;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public SubjectRevenueDTO() {
	}

}
