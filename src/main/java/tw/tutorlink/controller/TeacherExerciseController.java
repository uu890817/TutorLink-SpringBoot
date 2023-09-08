package tw.tutorlink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tw.tutorlink.bean.Exercises;
import tw.tutorlink.bean.OrderItem;
import tw.tutorlink.bean.Users;
import tw.tutorlink.dto.exercises.ResponseDTO;
import tw.tutorlink.dto.exercises.TeacherGetAllExerciseDTO;
import tw.tutorlink.dto.exercises.TeacherGetAllLessonsNameDTO;
import tw.tutorlink.dto.exercises.TeacherGetExerciseInfoDTO;
import tw.tutorlink.dto.exercises.TeacherGetLessonStudentDTO;
import tw.tutorlink.repository.ExercisesDAO;
import tw.tutorlink.service.ExercisesService;
import tw.tutorlink.service.TopicsService;

@RestController
@RequestMapping("/teacher")
public class TeacherExerciseController {

	@Autowired
	ExercisesService eService;
	@Autowired
	TopicsService tService;
	@Autowired
	ExercisesDAO e;
	private ResponseDTO resDTO;
	
	
	@GetMapping("/testApi")
	public List<OrderItem> testApi() {
		return e.findOrderByLessonId(2);
	}
	
	
	@GetMapping("/myAllExercise")
	@ResponseBody
	public List<TeacherGetAllExerciseDTO> getMyExercise(HttpSession session, @CookieValue("UsersId") String cookie) {
		Users uSession = (Users) session.getAttribute("logState");
		if(uSession.getUsersId() != null) {
			System.err.println("Session" + uSession.getUsersId());
			return eService.getTeacherExercise(uSession.getUsersId());
		}
		if(cookie != null) {
			System.err.println("Cookie" + cookie);
			return eService.getTeacherExercise(Integer.parseInt(cookie));
		}
		return null;
	}
	
	@GetMapping("/myExercise/{eId}")
	public TeacherGetExerciseInfoDTO getTopics(@PathVariable Integer eId){
		return eService.getExerciseByExerId(eId);
	}
	
	@GetMapping("/myLessons")
	public List<TeacherGetAllLessonsNameDTO> getLessons(HttpSession session){
		return eService.getLessonsName(1);
	}
	
	
	@GetMapping("/getStudents/{eId}")
	public List<TeacherGetLessonStudentDTO> getStudentsByLesson(@PathVariable Integer eId) {
		return eService.getStudentByLessonId(eId);
	}
	
	
	
	@PostMapping(path = "/newExercise", produces="application/json;charset=UTF-8")
	public String insertNewExercise(@RequestBody Exercises newExercise) {
		Users user = new Users();
		user.setUsersId(1);
		newExercise.setUsers(user);
		System.out.println(newExercise.getUsers().getUsersId());
		Exercises result = eService.insertNewExercise(newExercise);
		
		if(result != null) {
			return "OK";
		}
		return "Error";
	}
	
	
	
	@PutMapping(path = "/updateExercise", produces="application/json;charset=UTF-8")
	public String updataExercise(@RequestBody Exercises newExercise) {
		Users user = new Users();
		user.setUsersId(1);
		newExercise.setUsers(user);
		Exercises result = eService.insertNewExercise(newExercise);
		
		if(result != null) {
			return "OK";
		}
		return "Error";
	}
	
	
	@DeleteMapping(path = "/deleteExercise/{eId}")
	public String updataExercise(@PathVariable Integer eId) {
		System.out.println(eId);
		
		return eService.deleteExercise(eId);
	}
	
	
	
	
	
	
	
	
}
