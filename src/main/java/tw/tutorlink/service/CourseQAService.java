package tw.tutorlink.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.CourseQA;
import tw.tutorlink.bean.Lessons;
import tw.tutorlink.bean.Users;
import tw.tutorlink.repository.CourseQADAO;

@Service
public class CourseQAService {

	@Autowired
	private CourseQADAO cDAO;
	
	@Autowired
	public CourseQAService(CourseQADAO cDAO) {
		this.cDAO = cDAO;
	}
	
	public CourseQA saveCourseQA(CourseQA courseQA) {
        return cDAO.save(courseQA);
    }

    public CourseQA getCourseQAById(Integer courseQAId) {
        return cDAO.findById(courseQAId).orElse(null);
    }

    public List<CourseQA> getAllCourseQA() {
        return cDAO.findAll();
    }

    public void deleteCourseQA(Integer courseQAId) {
    	cDAO.deleteById(courseQAId);
    }
	
    //查詢此課程所有問答
	public List<CourseQA> getCourseQAByLessonId(Integer lessonId){
		return cDAO.findByLesson_LessonId(lessonId);
	}
	
	//新增一筆問題
	public CourseQA addCourseQA(Lessons lesson,Users user,String title,String question) {
		CourseQA courseQA = new CourseQA();
		courseQA.setLesson(lesson);
        courseQA.setUsers(user);
		courseQA.setTitle(title);
        courseQA.setQuestion(question);
		return cDAO.save(courseQA);
	}
	
	// 回答問題
    public CourseQA answerCourseQA(Integer courseQAId, String answer, Date time) {
        Optional<CourseQA> optionalCourseQA = cDAO.findById(courseQAId);

        if (optionalCourseQA.isPresent()) {
            CourseQA courseQA = optionalCourseQA.get();

            if (answer != null && !answer.isEmpty()) {
                courseQA.setAnswer(answer);
                courseQA.setTime(time);
                return cDAO.save(courseQA);
            }
        }
        return null;
    }
}
