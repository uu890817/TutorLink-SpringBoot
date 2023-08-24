package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.repository.StudentWillLearnDAO;

@Service
public class StudentWillLearnService {

	@Autowired
	private StudentWillLearnDAO swlDAO;
}
