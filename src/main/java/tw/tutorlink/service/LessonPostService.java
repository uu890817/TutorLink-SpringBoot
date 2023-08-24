package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.repository.LessonPostDAO;

@Service
public class LessonPostService {

	@Autowired
	private LessonPostDAO lpDAO;
}
