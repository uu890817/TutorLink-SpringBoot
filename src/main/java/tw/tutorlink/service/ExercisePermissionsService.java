package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.ExercisePermissions;
import tw.tutorlink.repository.ExercisePermissionsDAO;

@Service
public class ExercisePermissionsService {

	@Autowired
	private ExercisePermissionsDAO epDAO;
	
	
	public ExercisePermissions shareExercise(ExercisePermissions exercisePermissions) {
		ExercisePermissions result = epDAO.save(exercisePermissions);
		return (result != null) ? result: null;
		
	}
	
	
	
}
