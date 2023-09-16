package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.Question;
import tw.tutorlink.repository.QuestionsDAO;

@Service
public class QuestionsService {
	@Autowired
	private QuestionsDAO qDAO;
	
	
	public Question insertNewQuestion(Question q) {
		return qDAO.save(q);
	}
	
	
	
}
