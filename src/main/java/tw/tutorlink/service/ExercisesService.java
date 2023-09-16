package tw.tutorlink.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.CartItem;
import tw.tutorlink.bean.ExercisePermissions;
import tw.tutorlink.bean.Exercises;
import tw.tutorlink.bean.Lessons;
import tw.tutorlink.dto.exercises.StudentGetExerciseDTO;
import tw.tutorlink.dto.exercises.TeacherGetAllExerciseDTO;
import tw.tutorlink.dto.exercises.TeacherGetAllLessonsNameDTO;
import tw.tutorlink.dto.exercises.TeacherGetExerciseInfoDTO;
import tw.tutorlink.dto.exercises.TeacherGetLessonStudentDTO;
import tw.tutorlink.dto.exercises.TeacherShareExerciseDTO;
import tw.tutorlink.repository.ExercisePermissionsDAO;
import tw.tutorlink.repository.ExercisesDAO;

@Service
public class ExercisesService {

	@Autowired
	private ExercisesDAO eDAO;
	@Autowired
	private ExercisePermissionsDAO epDAO;
	
	

//	SELECT-----------------------------------------------------------------------------------------------
	// TEACHER
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

	public TeacherGetExerciseInfoDTO getExerciseByExerId(Integer exerId) {
		TeacherGetExerciseInfoDTO tDTO = new TeacherGetExerciseInfoDTO(eDAO.findExercisesByExerId(exerId));
		return tDTO;
	}

//	public List<OrderItem> getStudentByLessonId(Integer lessonId) {
//		return eDAO.findOrderByLessonId(lessonId);
//
//	}
	public List<TeacherGetLessonStudentDTO> getStudentByLessonId(Integer lId, Integer eId){
		List<CartItem> carts = eDAO.findStudents(lId);
		List<TeacherGetLessonStudentDTO> tDTOs = new ArrayList<>();
		
		for(CartItem cart:carts ) {
			System.err.println(cart.getUsers().getUserDetailUserName());
			TeacherGetLessonStudentDTO tDTO = new TeacherGetLessonStudentDTO();
			boolean hasSame = false; 
			for(TeacherGetLessonStudentDTO t: tDTOs) {
				if(cart.getUsers().getUsersId() == t.getUsersId() && cart.getUsers().getUserDetailUserName() == t.getUserName()) {
					System.err.println("重複使用者");
					hasSame = true;
				}
			}
			if(!hasSame) {
				tDTO.setUsersId(cart.getUsers().getUsersId());
				tDTO.setUserName(cart.getUsers().getUserDetailUserName());
				
				ExercisePermissions ep = this.getExercisePermissionsByUIdAndLId(eId, cart.getUsers().getUsersId());
				if(ep != null) {
					tDTO.setExerPerId(ep.getExerPerId());
					tDTO.setExerPermissions(ep);
				}
			
				Exercises e = eDAO.findExercisesByExerId(eId);		
				tDTO.setExerConfig(e.getExerciseConfig());
				tDTOs.add(tDTO);
			}
		}
		return tDTOs;
	}


	
	
	public ExercisePermissions getExercisePermissionsByUIdAndLId(Integer eId, Integer uId) {
		return eDAO.findExercisePermissionsByLessonIdAndUserId(eId, uId);
	}

	// STUDENT
//	public StudentGetExerciseDTO studentGetExerciseByExerId(Integer eId) {
//		StudentGetExerciseDTO sDTO = new StudentGetExerciseDTO(eDAO.findExercisesByExerId(eId));
//		return sDTO;
//	}

//	INSERT-----------------------------------------------------------------------------------------------
	public Exercises insertNewExercise(Exercises esercise) {
		Exercises result = eDAO.save(esercise);
		if (result != null) {
			return result;
		}
		return null;
	}

//	UPDATE-----------------------------------------------------------------------------------------------
	public Exercises updateExercise(Exercises esercise) {
		Exercises result = eDAO.save(esercise);
		if (result != null) {
			return result;
		}
		return null;
	}

//	DELETE-----------------------------------------------------------------------------------------------
	public String deleteExercise(Integer eId) {
		eDAO.deleteById(eId);
		return "OK";
	}

	public String deleteExercisePermission(Integer epId) {
		System.err.println(epId);
		epDAO.deleteById(epId);
		return "OK";
	}

	public StudentGetExerciseDTO studentGetExerciseByExerId(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
