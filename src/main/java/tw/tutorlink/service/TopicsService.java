package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.repository.TopicsDAO;

@Service
public class TopicsService {
	
	@Autowired
	private TopicsDAO tDAO;

}
