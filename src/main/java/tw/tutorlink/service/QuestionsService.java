package tw.tutorlink.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.Answer;
import tw.tutorlink.bean.Question;
import tw.tutorlink.dto.exercises.AnswerDTO;
import tw.tutorlink.dto.exercises.QuestionDTO;
import tw.tutorlink.repository.QuestionsDAO;

@Service
public class QuestionsService {
	@Autowired
	private QuestionsDAO qDAO;

	public Question findById(Integer qId) {
		Question question = qDAO.findById(qId).get();
		return question;
	}
	
	
	public List<QuestionDTO> getAllQuestion(Integer eId, Integer uId) {
		List<QuestionDTO> qDTOs = new ArrayList<>();
		List<Question> questions = qDAO.findAllQuestionByeId(eId);
		for (Question question : questions) {
			QuestionDTO qDTO = new QuestionDTO(question);
			if (question.getUsers().getUsersId() == uId) {
				qDTO.setMyQuestion(true);
			} else {
				qDTO.setMyQuestion(false);
			}
			List<Answer> answers = question.getAnswer();
			List<AnswerDTO> aDTOs = new ArrayList<>();
			for (Answer answer : answers) {
				AnswerDTO aDTO = new AnswerDTO(answer);
				if (answer.getUsers().getUsersId() == uId) {
					aDTO.setMyQuestion(true);
				}
				if(!answer.isDelete()) {
					
					aDTOs.add(aDTO);
				}
			}
			qDTO.setAnswer(aDTOs);
			qDTOs.add(qDTO);
		}

		return qDTOs;
	}

	public Question insertNewQuestion(Question q) {
		return qDAO.save(q);
	}

}
