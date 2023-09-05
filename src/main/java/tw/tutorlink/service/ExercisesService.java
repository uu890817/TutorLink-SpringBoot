package tw.tutorlink.service;

import java.util.ArrayList;
import java.util.List;

import org.conscrypt.Conscrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.Exercises;
import tw.tutorlink.bean.Lessons;
import tw.tutorlink.dto.exercises.TeacherGetAllExerciseDTO;
import tw.tutorlink.dto.exercises.TeacherGetAllLessonsName;
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
	
	public List<TeacherGetAllLessonsName> getLessonsName(Integer usersId) {
		List<TeacherGetAllLessonsName> tDTOs = new ArrayList<>();
		List<Lessons> lessons = eDAO.findLessonsByUsers(usersId);
		for(Lessons lesson: lessons) {
			TeacherGetAllLessonsName tDto = new TeacherGetAllLessonsName(lesson);
			tDTOs.add(tDto);
		}
		return tDTOs;
	}
	
	
	
	
}
