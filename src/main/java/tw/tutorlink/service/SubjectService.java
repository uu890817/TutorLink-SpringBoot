package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.repository.SubjectDAO;

@Service
public class SubjectService {

	@Autowired
	private SubjectDAO sDAO;
}
