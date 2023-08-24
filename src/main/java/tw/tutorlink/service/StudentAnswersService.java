package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.repository.StudentAnswersDAO;

@Service
public class StudentAnswersService {

	@Autowired
	private StudentAnswersDAO saDAO;
}
