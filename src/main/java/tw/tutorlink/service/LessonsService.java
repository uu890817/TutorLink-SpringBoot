package tw.tutorlink.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.tutorlink.bean.LessonDetail;
import tw.tutorlink.bean.Lessons;
import tw.tutorlink.bean.UserDetail;
import tw.tutorlink.bean.Users;
import tw.tutorlink.dto.lessons.FindAllLessonTypeLessonDTO;
import tw.tutorlink.dto.lessons.LessonsUpdateDTO;
import tw.tutorlink.dto.lessontool.finAllLessonsDTO;
import tw.tutorlink.repository.LessonDetailDAO;
import tw.tutorlink.repository.LessonsDAO;
import tw.tutorlink.repository.UserDetailDAO;
import tw.tutorlink.repository.UsersDAO;

@Service
public class LessonsService {

	@Autowired
	private LessonsDAO lDAO;

	@Autowired
	private UsersDAO uDAO;

	@Autowired
	private LessonDetailDAO ldDAO;

	@Autowired
	private UserDetailDAO udDAO;

//	public LessonsService(LessonsDAO lDAO) {
//		this.lDAO = lDAO;
//	}

	// 查詢全部課程資料
	public List<Lessons> getAllLessons() {
		return lDAO.findAll();
	}

	// 靠UserID查這位User全部課程資料
	public List<Lessons> getUserAllLessons(int id, boolean lessonType) {
		Users user = uDAO.findById(id);
		if (user != null) {
			return lDAO.findByUsers_UsersIdAndLessonType(user.getUsersId(), lessonType);
		}
		return null;
	}

	// 靠課程ID查詢資料
	public Lessons findLessonsById(Lessons lesson) {
		Optional<Lessons> lessons = lDAO.findById(lesson.getLessonId());
		if (lessons.isPresent()) {

			return lessons.get();
		}
		return null;
	}

	// 靠課程ID查詢資料加入購物車
	public Lessons AddLessonIntoCartById(int lid) {
		Lessons lessons = lDAO.findById(lid);
		return lessons;

	}

	// 新增課程

	public Lessons insertLesson(Lessons lesson) {

		return lDAO.save(lesson);

	}

	public Lessons insertLesson(int id, Lessons lesson, LessonDetail lessonDetail) {
		Users user = uDAO.findById(id);
		System.out.println("會員ID : " + user);
		if (user != null) {
			lesson.setUsers(user);
			lesson.setLessondetail(lessonDetail);
			lDAO.save(lesson);
			return lesson;

		}

		System.out.println("課程資料 : " + lesson);
		return lesson;

	}

//	// 修改課程
//	public Lessons updateLesson(Integer id, Lessons lesson, LessonDetail lessondetail) {
//		Lessons lessons = lDAO.findById(id).get();
//		System.out.println("132");
//
//		System.out.println(lessons);
//		if (lessons != null) {
//			lessons.setLessonName(lesson.getLessonName());
//			lessons.setImage(lesson.getImage());
//			lessons.setPrice(lesson.getPrice());
//			lessons.setSubject(lesson.getSubject());
//			lessons.getLessondetail().setImformation(lessondetail.getImformation());
//			lessons.getLessondetail().setMeetingUrl(lessondetail.getMeetingUrl());
//			lessons.getLessondetail().setCreateTime(new Date());
//			lessons.getLessondetail().setLanguage(lessondetail.getLanguage());
//			lessons.getLessondetail().setCourseUrl(lessondetail.getCourseUrl());
//			lDAO.save(lessons);
//			return lessons;
//		}
//
//		return null;
//
//	}
	
    //修改課程
    @Transactional
    public Lessons updateLesson(Integer id,LessonsUpdateDTO lDTO, LessonDetail lessondetail) {
            Lessons lessons = lDAO.findById(id).get();


            if(lessons!=null) {
                lessons.setLessonId(id);
                lessons.setLessonName(lDTO.getLessonName());
                lessons.setImage(lDTO.getImage());
                lessons.setPrice(lDTO.getPrice());
                lessons.setSubject(lDTO.getSubject());
                
                
                LessonDetail ld = lessons.getLessondetail();
                ld.setImformation(lessondetail.getImformation());
                ld.setMeetingUrl(lessondetail.getMeetingUrl());
                ld.setCreateTime(new Date());
                ld.setLanguage(lessondetail.getLanguage());
                ld.setCourseUrl(lessondetail.getCourseUrl());
                ldDAO.save(ld);
                lDAO.save(lessons);
            
            }
            return lessons;
            
            
            
        }

	// 刪除課程
	public String deleteLessons(Lessons lesson) {
		Optional<Lessons> lessons = lDAO.findById(lesson.getLessonId());
		if (lessons.isPresent()) {
			lDAO.deleteById(lesson.getLessonId());
			return "刪除成功";
		}
		return "刪除失敗";
	}

	// 用User找課程
	public List<Lessons> findByUsers(Users userId) {
		return lDAO.findByUsers(userId);
	}

	// 用UsersId找課程
	public List<Lessons> findByUserIdAndLessonType(Integer userId, boolean lessonType) {
		return lDAO.findByUsers_UsersIdAndLessonType(userId, lessonType);
	}

	// 用lessonId找課
	public Optional<Lessons> findByLessonId(Integer lessonId) {
		return lDAO.findById(lessonId);
	}

	// 找老師
	public UserDetail findUserByLessonId(Integer lessonId) {
		Integer userId = lDAO.findUserIdByLessonId(lessonId);
		System.out.println(userId);

		if (userId != null) {
			return udDAO.findByUsers_usersId(userId);
		} else {
			return null;
		}
	}

	// Subject找課程
	public List<Lessons> findLessonsBySubIdAndType(Integer subjectId, boolean type) {
		return lDAO.findLessonsBySubIdAndType(subjectId, type);
	}
	
	//subjectId找課程
	public List<finAllLessonsDTO> findLessonsBySubId(Integer subjectId){
		List<Lessons> lessonsList = lDAO.findLessonsBySubId(subjectId);
		List<finAllLessonsDTO> courseDTOList = new ArrayList<>();
		for (Lessons lesson : lessonsList) {
	        Users teacher = uDAO.findById(lesson.getUsers().getUsersId()).get();
	        finAllLessonsDTO lDTO = new finAllLessonsDTO(lesson, teacher);
	        courseDTOList.add(lDTO);
	    }
	    return courseDTOList;
	}

	// type找影片課程
	public List<Lessons> findVideoLessonsByType() {
		return lDAO.findLessonsByType(false);
	}

	// type找線上課程
	public List<Lessons> findOnlineLessonsByType() {
		return lDAO.findLessonsByType(true);
	}

	public void deleteLessonById(Integer lessonId) {

        lDAO.deleteById(lessonId);
    }
	

	// 查詢所有課程資料
	public List<finAllLessonsDTO> findAllLesson() {
	    List<Lessons> LessonsList = lDAO.findAll();
	    List<finAllLessonsDTO> courseDTOList = new ArrayList<>();
	    for (Lessons lesson : LessonsList) {
	        Users teacher = uDAO.findById(lesson.getUsers().getUsersId()).get();
	        finAllLessonsDTO lDTO = new finAllLessonsDTO(lesson, teacher);
	        courseDTOList.add(lDTO);
	    }
	    return courseDTOList;
	}
	
	//查詢所有影片課程
	public List<FindAllLessonTypeLessonDTO> findAllVideoLesson() {
		List<Lessons> lessonList = lDAO.findLessonsByType(false);
		List<FindAllLessonTypeLessonDTO> courseDTOList = new ArrayList<>();
		for(Lessons lesson : lessonList) {
			Users teacher = uDAO.findById(lesson.getUsers().getUsersId()).get();
			FindAllLessonTypeLessonDTO VDTO = new FindAllLessonTypeLessonDTO(lesson, teacher);
			courseDTOList.add(VDTO);
		}
		return courseDTOList;
	}
	
	//查詢所有線上課程
	public List<FindAllLessonTypeLessonDTO> findAllOnlineLesson() {
		List<Lessons> lessonList = lDAO.findLessonsByType(true);
		List<FindAllLessonTypeLessonDTO> courseDTOList = new ArrayList<>();
		for (Lessons lesson : lessonList) {
			Users teacher = uDAO.findById(lesson.getUsers().getUsersId()).get();
			FindAllLessonTypeLessonDTO VDTO = new FindAllLessonTypeLessonDTO(lesson, teacher);
			courseDTOList.add(VDTO);
		}
		return courseDTOList;
	}
	
	//查詢所有線上課程
	public List<FindAllLessonTypeLessonDTO> findAllLessons() {
		List<Lessons> lessonList = lDAO.findAll();
		List<FindAllLessonTypeLessonDTO> courseDTOList = new ArrayList<>();
		for (Lessons lesson : lessonList) {
			Users teacher = uDAO.findById(lesson.getUsers().getUsersId()).get();
			FindAllLessonTypeLessonDTO VDTO = new FindAllLessonTypeLessonDTO(lesson, teacher);
			courseDTOList.add(VDTO);
		}
		return courseDTOList;
	}
	
	//subject查詢所有課程
	public List<FindAllLessonTypeLessonDTO> findLessonBySubject(Integer subId){
		List<Lessons> lessonList = lDAO.findLessonsBySubId(subId);
		List<FindAllLessonTypeLessonDTO> courseDTOList = new ArrayList<>();
		for(Lessons lesson : lessonList) {
			Users teacher = uDAO.findById(lesson.getUsers().getUsersId()).get();
			FindAllLessonTypeLessonDTO VDTO = new FindAllLessonTypeLessonDTO(lesson, teacher);
			courseDTOList.add(VDTO);
		}
		return courseDTOList;
	}
	//後臺課程分頁
	public List<Lessons> findAllLessons(int page, int rows) {
		if (page == 0 && rows == 0) {
			return lDAO.findAll();
		}
		Pageable pageable = PageRequest.of(page, rows);
		Page<Lessons> result = lDAO.findAll(pageable);
		return result.getContent();
	}
	
	
	public long count() {
		return lDAO.count();
	}


}