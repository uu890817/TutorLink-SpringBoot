package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.StudentAnswers;
import tw.tutorlink.repository.StudentAnswersDAO;

@Service
public class StudentAnswersService {

	@Autowired
	private StudentAnswersDAO saDAO;

	public String insertStudentAns(StudentAnswers sAns) {
		StudentAnswers result = null;
		try {
			result = saDAO.save(sAns);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result == null ? "Error" : "OK";
	}

}
