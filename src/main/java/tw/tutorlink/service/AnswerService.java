package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.Answer;
import tw.tutorlink.bean.Question;
import tw.tutorlink.repository.AnswerDAO;

@Service
public class AnswerService {

	@Autowired
	private AnswerDAO aDAO;
	
	public Answer insertNewAnswer(Answer q) {
		return aDAO.save(q);
	}
	
	public Answer findById(Integer aId) {
		return aDAO.findById(aId).get();
	}
	
	
	
	
	
	
	
	
	
}
