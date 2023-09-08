package tw.tutorlink.service;

import java.util.ArrayList;
import java.util.List;

import org.conscrypt.Conscrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.Exercises;
import tw.tutorlink.bean.Lessons;
import tw.tutorlink.bean.OrderItem;
import tw.tutorlink.dto.exercises.TeacherGetAllExerciseDTO;
import tw.tutorlink.dto.exercises.TeacherGetAllLessonsNameDTO;
import tw.tutorlink.dto.exercises.TeacherGetExerciseInfoDTO;
import tw.tutorlink.dto.exercises.TeacherGetLessonStudentDTO;
import tw.tutorlink.repository.ExercisesDAO;

@Service
public class ExercisesService {

	@Autowired
	private ExercisesDAO eDAO;

	public List<TeacherGetAllExerciseDTO> getTeacherExercise(Integer usersId) {
		List<TeacherGetAllExerciseDTO> tDTOs = new ArrayList<>();
		List<Exercises> exers = eDAO.findByUsers(usersId);
		for (Exercises exer : exers) {
			TeacherGetAllExerciseDTO tDto = new TeacherGetAllExerciseDTO(exer);
			tDTOs.add(tDto);
		}
		return tDTOs;
	}

	public List<TeacherGetAllLessonsNameDTO> getLessonsName(Integer usersId) {
		List<TeacherGetAllLessonsNameDTO> tDTOs = new ArrayList<>();
		List<Lessons> lessons = eDAO.findLessonsByUsers(usersId);
		for (Lessons lesson : lessons) {
			TeacherGetAllLessonsNameDTO tDto = new TeacherGetAllLessonsNameDTO(lesson);
			tDTOs.add(tDto);
		}
		return tDTOs;
	}

	public Exercises insertNewExercise(Exercises esercise) {
		Exercises result = eDAO.save(esercise);
		if (result != null) {
			return result;
		}
		return null;
	}

	public TeacherGetExerciseInfoDTO getExerciseByExerId(Integer exerId) {
		TeacherGetExerciseInfoDTO tDTO = new TeacherGetExerciseInfoDTO(eDAO.findExercisesByExerId(exerId));
		return tDTO;
	}

	public Exercises updateExercise(Exercises esercise) {
		Exercises result = eDAO.save(esercise);
		if (result != null) {
			return result;
		}
		return null;
	}

	public String deleteExercise(Integer eId) {
		eDAO.deleteById(eId);
		return "OK";
	}
	
	
	
	public List<TeacherGetLessonStudentDTO> getStudentByLessonId(Integer lessonId) {
		List<TeacherGetLessonStudentDTO> students = new ArrayList<>();
		List<OrderItem> orders = eDAO.findOrderByLessonId(lessonId);
		
		for(OrderItem order: orders) {
			if(order.getOrderStates() != 0) {
				break;
			}
			 TeacherGetLessonStudentDTO tDTO = new TeacherGetLessonStudentDTO(order);
			 students.add(tDTO);
		}
		
		
//		eDAO.findOrderByLessonId(lessonId)
		
		return students;
	}
	
	
	
	
	
	
	
	
	

}
