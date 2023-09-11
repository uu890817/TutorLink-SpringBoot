package tw.tutorlink.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.Calender;
import tw.tutorlink.bean.Lessons;
import tw.tutorlink.bean.Report;
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

}
