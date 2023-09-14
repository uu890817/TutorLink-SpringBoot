package tw.tutorlink.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.LessonDetail;
import tw.tutorlink.bean.Lessons;
import tw.tutorlink.repository.LessonDetailDAO;
import tw.tutorlink.repository.LessonsDAO;

@Service
public class LessonDetailService {

	@Autowired
	private LessonDetailDAO ldDAO;
	
	@Autowired
	private LessonsDAO lDAO;
	
	//查詢全部課程明細
	public List<LessonDetail> getAllLessonDetail(){
		return ldDAO.findAll();
	}
	
	//靠課程明細ID查詢課程明細
	public LessonDetail findLessonDetailById(LessonDetail lessonDetail){
		Optional<LessonDetail> LessonDetail = ldDAO.findById(lessonDetail.getLessonDetailId());
		if(LessonDetail.isPresent()) {
			return LessonDetail.get();
		}
		return null;
	}
	
	//靠課程ID查詢課程明細
	public LessonDetail findLessonDetailByLessonId(Integer id) {
		Lessons lesson = lDAO.findById(id).get();
		if(lesson!=null) {
			Optional<LessonDetail> lD =	ldDAO.findById(lesson.getLessonId());
			if(lD.isPresent()) {
				return lD.get();
			}
			
		}
		return null;
	}
	
	//新增課程明細
	public LessonDetail insertLessonDetail(LessonDetail lessonDetail) {
		return ldDAO.save(lessonDetail);
	}
	
	//修改課程明細
	public LessonDetail updateLessonDetail(int id,LessonDetail lessondetail) {
			Lessons lessonId = lDAO.findById(id);
			if(lessonId!=null) {
				lessondetail.setLessonDetailId(lessonId.getLessonId());
				lessondetail.setImformation(lessondetail.getImformation());
				lessondetail.setCourseTotalTime(lessondetail.getCourseTotalTime());
				lessondetail.setCourseUrl(lessondetail.getCourseUrl());
				lessondetail.setMeetingUrl(lessondetail.getMeetingUrl());
				return ldDAO.save(lessondetail);
			}
			return null;
			
//		Optional<LessonDetail> LessonDetail = ldDAO.findById(lessonDetail.getLessonDetailId());
//		if(LessonDetail.isPresent()) {
//			LessonDetail result = LessonDetail.get();
//			result.setMeetingUrl(lessonDetail.getMeetingUrl());
//			result.setImformation(lessonDetail.getImformation());
//			result.setCourseUrl(lessonDetail.getCourseUrl());
//		
//			return ldDAO.save(result);
//		}
//		
//		return null;
//	}
	
	
	}
	
	//查課程影片url
	public String findPreVideo(Integer lessonId) {
		return ldDAO.findCourseUrlByLessonId(lessonId);
	}
}
