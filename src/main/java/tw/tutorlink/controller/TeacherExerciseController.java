package tw.tutorlink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.http.HttpSession;
import tw.tutorlink.bean.Exercises;
import tw.tutorlink.bean.Topics;
import tw.tutorlink.bean.Users;
import tw.tutorlink.dto.exercises.TeacherGetAllExerciseDTO;
import tw.tutorlink.dto.exercises.TeacherGetAllLessonsNameDTO;
import tw.tutorlink.dto.exercises.TeacherGetExerciseInfoDTO;
import tw.tutorlink.service.ExercisesService;
import tw.tutorlink.service.TopicsService;

@RestController
@RequestMapping("/teacher")
public class TeacherExerciseController {

	@Autowired
	ExercisesService eService;
	@Autowired
	TopicsService tService;
	
	@GetMapping("/myAllExercise")
	@ResponseBody
	public List<TeacherGetAllExerciseDTO> getMyExercise(HttpSession session) {
		return eService.getTeacherExercise(1);
	}
	
	@GetMapping("/myExercise/{eId}")
	public TeacherGetExerciseInfoDTO getTopics(@PathVariable Integer eId){
		return eService.getExerciseByExerId(eId);
	}
	
	@GetMapping("/myLessons")
	public List<TeacherGetAllLessonsNameDTO> getLessons(HttpSession session){
		return eService.getLessonsName(1);
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
