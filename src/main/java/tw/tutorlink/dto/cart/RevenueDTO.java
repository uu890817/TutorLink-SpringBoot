package tw.tutorlink.dto.cart;

public class RevenueDTO {
	private Integer videos;
	private Integer lessons;
	
	public RevenueDTO(Integer videos, Integer lessons) {
		super();
		this.videos = videos;
		this.lessons = lessons;
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

	public RevenueDTO() {
	}

}
