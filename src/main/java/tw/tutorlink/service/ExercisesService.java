package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.repository.ExercisesDAO;

@Service
public class ExercisesService {

	@Autowired
	private ExercisesDAO eDAO;
}