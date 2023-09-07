package tw.tutorlink.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.Lessons;
import tw.tutorlink.bean.Report;
import tw.tutorlink.bean.Users;
import tw.tutorlink.repository.LessonsDAO;
import tw.tutorlink.repository.ReportDAO;
import tw.tutorlink.repository.UsersDAO;

@Service
public class ReportService {

	@Autowired
	private ReportDAO rDAO;
	
	@Autowired
	private LessonsDAO lDAO;
	
	@Autowired
	private UsersDAO uDao;

	// 新增
	public void insert(Report rp) {
		rDAO.save(rp);
	}

	// ID查詢
	public Report findById(Integer id) {
		Optional<Report> optional = rDAO.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	// 使用者ID查詢
	public List<Report> findReportListByUsersId(Integer usersId) {
		List<Report> scoreList = rDAO.findReportListByUsersId(usersId);
		return scoreList;
	}
	
	
	// 課程ID查詢
	public List<Report> findReportListByLessonId(Integer lessonId) {
		List<Report> scoreList = rDAO.findReportListByLessonId(lessonId);
		return scoreList;
	}
	
	// ID刪除
	public void deleteById(Integer id) {
		rDAO.deleteById(id);
	}
	
	
	// 查詢全部
	public List<Report> findAllReportList() {
		List<Report> scoreList = rDAO.findAllReportList();
		return scoreList;
	}

	// 課程ID查詢課程
	public Lessons findLessonsById(Integer id){
		Optional<Lessons> lessons = lDAO.findById(id);
		if(lessons.isPresent()) {
			return lessons.get();
		}
		return null;
	}
	
	// 使用者ID查詢使用者
	public Users findUserId(Integer uID) {
		return uDao.findById(uID).get();
	}
}
