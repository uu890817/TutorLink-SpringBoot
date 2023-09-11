package tw.tutorlink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tw.tutorlink.bean.Exercises;
import tw.tutorlink.bean.Users;
import tw.tutorlink.dto.exercises.ResponseDTO;
import tw.tutorlink.service.ExercisePermissionsService;
import tw.tutorlink.service.ExercisesService;

@RestController
@RequestMapping("/student")
public class StudentExerciseController {

	@Autowired
	ExercisePermissionsService epService;
	@Autowired
	ExercisesService eService;

	@GetMapping("/test")
	public Exercises testApi() {
		return eService.studentGetExerciseByExerId(1);
	}
	
	
	@GetMapping("/myAllExercise")
	public ResponseDTO getMyExercise(HttpSession session) {
		Users uSession = (Users) session.getAttribute("logState");
		if (uSession != null) {
			System.err.println("Session" + uSession.getUsersId());
			return new ResponseDTO(epService.studentGetAllExercise(uSession.getUsersId()), 200, "OK");
		}
		return new ResponseDTO(null, 500, "請登入");
	}
		
	
	
	
	
	
}
