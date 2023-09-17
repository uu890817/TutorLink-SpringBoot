package tw.tutorlink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.Lessons;
import tw.tutorlink.bean.StudentWillLearn;
import tw.tutorlink.repository.StudentWillLearnDAO;

@Service
public class StudentWillLearnService {

	@Autowired
	private StudentWillLearnDAO swlDAO;
	
	//新增WillLearn
	public StudentWillLearn insertStudentWillLearn(String willLearn,Integer lessonId) {
		Lessons lesson = new Lessons();
		lesson.setLessonId(lessonId);
		StudentWillLearn studentWillLearn = new StudentWillLearn();
		studentWillLearn.setWillLearnContent(willLearn);
		studentWillLearn.setLesson(lesson);
		return swlDAO.save(studentWillLearn);
	}
	
	//取得課程WillLeaern
	public List<StudentWillLearn> getLessonWillLearn(Integer lessonId){
		return swlDAO.findByLesson_LessonId(lessonId);
	}
	
	//刪除一筆
	public void deleteWillLearn(Integer willLearnId) {
		if (swlDAO.existsById(willLearnId)) {
			swlDAO.deleteById(willLearnId);;
    	}
	}
	
}
