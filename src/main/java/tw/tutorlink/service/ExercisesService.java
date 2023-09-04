package tw.tutorlink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.Exercises;
import tw.tutorlink.repository.ExercisesDAO;

@Service
public class ExercisesService {

	@Autowired
	private ExercisesDAO eDAO;
	
	
	public List<Exercises> getTeacherExercise(Integer usersId) {
		return eDAO.findByUsers(usersId);
	}
	
	
	
	
}
