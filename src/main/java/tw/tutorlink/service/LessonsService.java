package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.repository.LessonsDAO;

@Service
public class LessonsService {

	@Autowired
	private LessonsDAO lDAO;
	
}
