package tw.tutorlink.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.LessonDetail;
import tw.tutorlink.repository.LessonDetailDAO;

@Service
public class LessonDetailService {

	@Autowired
	private LessonDetailDAO ldDAO;
	
	//查詢全部課程明細
	public List<LessonDetail> getAllLessonDetail(){
		return ldDAO.findAll();
	}
	
	//靠ID查詢課程明細
	public LessonDetail findLessonDetailById(LessonDetail lessonDetail){
		Optional<LessonDetail> LessonDetail = ldDAO.findById(lessonDetail.getLessonDetailId());
		if(LessonDetail.isPresent()) {
			return LessonDetail.get();
		}
		return null;
	}
	
	//新增課程明細
	public LessonDetail insertLessonDetail(LessonDetail lessonDetail) {
		return ldDAO.save(lessonDetail);
	}
	
	//修改課程明細
	public LessonDetail updateLessonDetail(LessonDetail lessonDetail) {
		Optional<LessonDetail> LessonDetail = ldDAO.findById(lessonDetail.getLessonDetailId());
		if(LessonDetail.isPresent()) {
			LessonDetail result = LessonDetail.get();
			result.setMeetingUrl(lessonDetail.getMeetingUrl());
			result.setImformation(lessonDetail.getImformation());
			result.setCourseUrl(lessonDetail.getCourseUrl());
		
			return ldDAO.save(result);
		}
		
		return null;
	}
	
	
	
}
