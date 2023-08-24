package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.repository.CalenderDAO;

@Service
public class CalenderService {

	@Autowired
	private CalenderDAO cDAO;
}
