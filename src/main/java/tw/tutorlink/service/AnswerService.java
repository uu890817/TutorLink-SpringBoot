package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.repository.AnswerDAO;

@Service
public class AnswerService {

	@Autowired
	private AnswerDAO aDAO;
}
