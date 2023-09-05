package tw.tutorlink.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.Exercises;
import tw.tutorlink.dto.exercises.TeacherGetAllExerciseDTO;
import tw.tutorlink.repository.ExercisesDAO;

@Service
public class ExercisesService {

	@Autowired
	private ExercisesDAO eDAO;
	
	
	public List<TeacherGetAllExerciseDTO> getTeacherExercise(Integer usersId) {
		List<TeacherGetAllExerciseDTO> tDTOs = new ArrayList<>();
		List<Exercises> exers = eDAO.findByUsers(usersId);
		for(Exercises exer: exers) {
			TeacherGetAllExerciseDTO tDto = new TeacherGetAllExerciseDTO(exer);
			tDTOs.add(tDto);
		}
		return tDTOs;
	}
	
	
	
	
}
