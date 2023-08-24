package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.repository.LessonDetailDAO;

@Service
public class LessonDetailService {

	@Autowired
	private LessonDetailDAO ldDAO;
}
