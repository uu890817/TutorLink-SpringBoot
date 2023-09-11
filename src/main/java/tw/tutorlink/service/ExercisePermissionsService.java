package tw.tutorlink.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.ExercisePermissions;
import tw.tutorlink.dto.exercises.StudentGetAllExerciseDTO;
import tw.tutorlink.repository.ExercisePermissionsDAO;

@Service
public class ExercisePermissionsService {

	@Autowired
	private ExercisePermissionsDAO epDAO;
	
	
	public ExercisePermissions shareExercise(ExercisePermissions exercisePermissions) {
		ExercisePermissions result = epDAO.save(exercisePermissions);
		return (result != null) ? result: null;
		
	}
	
	public List<StudentGetAllExerciseDTO> studentGetAllExercise(Integer uId) {
		List<StudentGetAllExerciseDTO> sDTOs= new ArrayList<>();
		List<ExercisePermissions> exercises = epDAO.findExercisePermissionsByuId(uId);
		for(ExercisePermissions exercise: exercises) {
			StudentGetAllExerciseDTO sDTO = new StudentGetAllExerciseDTO(exercise);
			sDTOs.add(sDTO);
		}
		
		return sDTOs;
	}
	
	
}
