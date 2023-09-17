package tw.tutorlink.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.Calender;
import tw.tutorlink.bean.CalenderDTO;
import tw.tutorlink.bean.Lessons;
import tw.tutorlink.bean.LessonsDTO;
import tw.tutorlink.bean.Users;
import tw.tutorlink.repository.CalenderDAO;
import tw.tutorlink.repository.LessonsDAO;
import tw.tutorlink.repository.UsersDAO;

@Service
public class CalenderService {

	@Autowired
	private CalenderDAO cDAO;

	@Autowired
	private LessonsDAO lDAO;

	@Autowired
	private UsersDAO uDao;

	// ID查詢
	public Calender findById(Integer id) {
		Optional<Calender> optional = cDAO.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	// 新增
	public void insert(Calender cd) {
		cDAO.save(cd);
	}

	// 使用者ID查詢
	public List<Calender> findCalenderListByUsersId(Integer usersId) {
		List<Calender> calenderList = cDAO.findCalenderListByUsersId(usersId);
		return calenderList;
	}

	// 課程ID查詢
	public List<Calender> findCalenderListByLessonId(Integer lessonId) {
		List<Calender> calenderList = cDAO.findCalenderListByLessonId(lessonId);
		return calenderList;
	}

	// ID刪除
	public void deleteById(Integer id) {
		cDAO.deleteById(id);
	}

	// 課程ID查詢課程
	public Lessons findLessonsById(Integer id) {
		Optional<Lessons> lessons = lDAO.findById(id);
		if (lessons.isPresent()) {
			return lessons.get();
		}
		return null;
	}

	// 使用者ID查詢使用者
	public Users findUserId(Integer uID) {
		return uDao.findById(uID).get();
	}

	// 透過使用者查詢行事曆中課程的老師詳細資料
	public List<LessonsDTO> findLessonsByUsersId(Integer usersId) {
		List<Calender> calenderList = cDAO.findCalenderListByUsersId(usersId);
		List<LessonsDTO> courseDTOList = new ArrayList<>();
		for (Calender calender : calenderList) {
			Lessons lessons = calender.getLesson();
			Users teacher = findUserId(lessons.getUsers().getUsersId());
			LessonsDTO lDTO = new LessonsDTO(lessons, teacher, calender);
			courseDTOList.add(lDTO);
		}

		return courseDTOList;
	}

	// 查詢所有課程的行事曆
	public List<CalenderDTO> findCalenderbyUsersAllLessons(int userId) {
		Users user = uDao.findById(userId);
		List<Lessons> lessonList = lDAO.findByUsers(user);
		List<CalenderDTO> courseDTOList = new ArrayList<>();
		for (Lessons lesson : lessonList) {
			List<Calender> calenderList = cDAO.findCalenderListByLessonId(lesson.getLessonId());
			for (Calender calender : calenderList) {
				Lessons lessons = calender.getLesson();
				Users student = findUserId(calender.getUsers().getUsersId());
				CalenderDTO lDTO = new CalenderDTO(lessons, student, calender);
				courseDTOList.add(lDTO);
			}
		}

		return courseDTOList;
	}
	
	
	// 透過課程查詢行事曆
	public List<CalenderDTO> findByLessonsId(int lessonId) {
		List<CalenderDTO> courseDTOList = new ArrayList<>();
		List<Calender> calenderList = cDAO.findCalenderListByLessonId(lessonId);
		for (Calender calender : calenderList) {
			Lessons lessons = calender.getLesson();
			Users student = findUserId(calender.getUsers().getUsersId());
			CalenderDTO lDTO = new CalenderDTO(lessons, student, calender);
			courseDTOList.add(lDTO);
		}

		return courseDTOList;
	}

}
