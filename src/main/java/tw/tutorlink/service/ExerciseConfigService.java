package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.repository.ExerciseConfigDAO;

@Service
public class ExerciseConfigService {
	@Autowired
	private ExerciseConfigDAO ecDAO;
}
