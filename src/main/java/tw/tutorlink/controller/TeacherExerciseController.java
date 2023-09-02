package tw.tutorlink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tw.tutorlink.bean.Exercises;
import tw.tutorlink.bean.Topics;
import tw.tutorlink.service.ExercisesService;
import tw.tutorlink.service.TopicsService;

@RestController
public class TeacherExerciseController {

	@Autowired
	ExercisesService eService;
	@Autowired
	TopicsService tService;
	
	@GetMapping("/myExercise")
	@ResponseBody
	public List<Exercises> getMyExercise() {
		return eService.getTeacherExercise();
	}
	
	@GetMapping("/myTopics")
	public List<Topics> getTopics(){
		return tService.getTopicsByExerciseId(1);
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
