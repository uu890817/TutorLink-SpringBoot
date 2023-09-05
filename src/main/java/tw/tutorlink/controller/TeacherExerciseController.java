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

import tw.tutorlink.bean.Exercises;
import tw.tutorlink.bean.Topics;
import tw.tutorlink.service.ExercisesService;
import tw.tutorlink.service.TopicsService;

@RestController
@RequestMapping("/teacher")
public class TeacherExerciseController {

	@Autowired
	ExercisesService eService;
	@Autowired
	TopicsService tService;
	
	@GetMapping("/myExercise")
	@ResponseBody
	public List<Exercises> getMyExercise(@RequestParam Integer teacherId) {
		return eService.getTeacherExercise(teacherId);
	}
	
	@GetMapping("/myTopics/{eId}")
	public List<Topics> getTopics(@PathVariable Integer eId){
		return tService.getTopicsByExerciseId(eId);
	}
	
	
	@PostMapping("/newExercise")
	public String insertNewExercise(@RequestBody Exercises newExercise) {
		
		return "OK";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
