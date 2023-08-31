package tw.tutorlink.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.Video;
import tw.tutorlink.repository.VideoDAO;

@Service
public class VideoService {
	
	@Autowired
	private VideoDAO vDAO;
	
	//新增一部影片
	public Video createVideo(Video video) {
		return vDAO.save(video);
	}
	
	//查詢此課程所有影片
	public List<Video> findVideoByLesson(Integer lessonDetailId){
		return vDAO.findByLessonDetail_LessonDetailId(lessonDetailId);
	}
	
	//刪除此課程所有影片
	public List<Video> deleteVideoByLesson(Integer lessonDetailId){
		return vDAO.deleteByLessonDetail_LessonDetailId(lessonDetailId);
	}
	
	// 修改影片資訊
    public Video updateVideo(Integer videoId, String chapterName, String courseUrl) {
        Optional<Video> optionalVideo = vDAO.findById(videoId);

        if (optionalVideo.isPresent()) {
            Video existingVideo = optionalVideo.get();
            existingVideo.setChapterName(chapterName);
            existingVideo.setCourseUrl(courseUrl);
            return vDAO.save(existingVideo);
        }

        return null; // 或者抛出一个异常，表示影片不存在
    }

}
