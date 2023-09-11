package tw.tutorlink.service;

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
}
