package tw.tutorlink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.tutorlink.dto.exercises.StudentGetAllExerciseDTO;
import tw.tutorlink.service.ExercisePermissionsService;

@RestController
@RequestMapping("/student")
public class StudentExerciseController {

	@Autowired
	ExercisePermissionsService epService;


	@GetMapping("/test")
	public List<StudentGetAllExerciseDTO> testApi() {
		return epService.studentGetAllExercise(2);
	}
	
	
	
}
