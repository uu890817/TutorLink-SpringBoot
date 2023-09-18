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
import tw.tutorlink.bean.Answer;
import tw.tutorlink.bean.ExercisePermissions;
import tw.tutorlink.bean.Exercises;
import tw.tutorlink.bean.Question;
import tw.tutorlink.bean.Users;
import tw.tutorlink.dto.exercises.AnswerDTO;
import tw.tutorlink.dto.exercises.QuestionDTO;
import tw.tutorlink.dto.exercises.ResponseDTO;
import tw.tutorlink.dto.exercises.TeacherGetAllExerciseDTO;
import tw.tutorlink.dto.exercises.TeacherGetAllLessonsNameDTO;
import tw.tutorlink.dto.exercises.TeacherGetExerciseInfoDTO;
import tw.tutorlink.dto.exercises.TeacherGetLessonStudentDTO;
import tw.tutorlink.dto.exercises.TeacherShareExerciseDTO;
import tw.tutorlink.repository.ExercisesDAO;
import tw.tutorlink.service.AnswerService;
import tw.tutorlink.service.ExercisePermissionsService;
import tw.tutorlink.service.ExercisesService;
import tw.tutorlink.service.QuestionsService;
import tw.tutorlink.service.TopicsService;

@RestController
@RequestMapping("/teacher")
public class TeacherExerciseController {

	@Autowired
	ExercisesService eService;
	@Autowired
	TopicsService tService;
	@Autowired
	ExercisePermissionsService epService;
	@Autowired
	QuestionsService qService;
	@Autowired
	AnswerService aService;
	@Autowired
	ExercisesDAO e;

	private ResponseDTO resDTO;

	public Integer getUserId(HttpSession session) {
		try {

			Users user = (Users) session.getAttribute("logState");
			return user.getUsersId();
		} catch (Exception e) {
			System.err.println("Session無法取得");
			return null;
		}

	}

	@GetMapping("/testApi")
	public List<Exercises> testApi() {
		return e.findByUsers(1);
//		return e.findOrderByLessonId(2);
	}

	@GetMapping("/myAllExercise")
	@ResponseBody
	public List<TeacherGetAllExerciseDTO> getMyExercise(HttpSession session) {
		Users uSession = (Users) session.getAttribute("logState");
		if (uSession != null) {
			System.err.println("Session" + uSession.getUsersId());
			return eService.getTeacherExercise(uSession.getUsersId());
		}
		return null;
	}

	@GetMapping("/myExercise/{eId}")
	public TeacherGetExerciseInfoDTO getTopics(@PathVariable Integer eId) {
		return eService.getExerciseByExerId(eId);
	}

	@GetMapping("/myLessons")
	public List<TeacherGetAllLessonsNameDTO> getLessons(HttpSession session, @CookieValue("UsersId") String cookie) {
		Users uSession = (Users) session.getAttribute("logState");
		if (uSession != null) {
			return eService.getLessonsName(uSession.getUsersId());
		}
		if (cookie != null) {
			return eService.getLessonsName(Integer.parseInt(cookie));
		}
		return null;
	}

	@GetMapping("/getStudents/{lId}/{eId}")
	public List<TeacherGetLessonStudentDTO> getStudentsByLesson(@PathVariable Integer lId, @PathVariable Integer eId) {
		List<TeacherGetLessonStudentDTO> tDTOs = eService.getStudentByLessonId(lId, eId);

		return tDTOs;
	}

	@GetMapping("/getAllQuestion/{eId}")
	public ResponseDTO getAllQuestion(@PathVariable Integer eId, HttpSession session) {
		Integer userId = this.getUserId(session);
		if (userId != null) {
			List<QuestionDTO> allQuestion = qService.getAllQuestion(eId, userId);
			ResponseDTO responseDTO = new ResponseDTO(allQuestion, 200, "OK");

			return responseDTO;
		}
		return new ResponseDTO(null, 500, "Error");
	}

	@PostMapping(path = "/newExercise", produces = "application/json;charset=UTF-8")
	public String insertNewExercise(@RequestBody Exercises newExercise, HttpSession session,
			@CookieValue("UsersId") String cookie) {
		Users uSession = (Users) session.getAttribute("logState");
		Users user = new Users();
		if (uSession != null) {
			user.setUsersId(uSession.getUsersId());
		}
		if (cookie != null) {
			user.setUsersId(Integer.parseInt(cookie));
		}
		newExercise.setUsers(user);
		System.out.println(newExercise.getUsers().getUsersId());
		Exercises result = eService.insertNewExercise(newExercise);

		if (result != null) {
			return "OK";
		}
		return "Error";
	}

	@PostMapping(path = "/shareExercise", produces = "application/json;charset=UTF-8")
	public String insertNewExercise(@RequestBody TeacherShareExerciseDTO newExercisePermissions) {
		ExercisePermissions newEP = new ExercisePermissions();
		newEP.setExerPerId(newExercisePermissions.getExerPerId());
		newEP.setUsers(newExercisePermissions.getUsers());
		newEP.setExerciseConfig(newExercisePermissions.getExerciseConfig());
		newEP.setExercises(newExercisePermissions.getExercises());

//		System.err.println(newExercisePermissions.getExerciseConfig().getType());
//		System.err.println(newExercisePermissions.getUsers().getUsersId());
//		return "123";

		ExercisePermissions result = epService.shareExercise(newEP);
		return result != null ? "OK" : "Error";
	}

	@PostMapping("/addNewAnswer")
	public ResponseDTO addNewQuestion(@RequestBody Answer ans, HttpSession session) {
		Integer userId = this.getUserId(session);
		if (userId != null) {
			Users u = new Users();
			u.setUsersId(userId);
			ans.setUsers(u);

			Answer answer = aService.insertNewAnswer(ans);
			AnswerDTO aDTO = new AnswerDTO(answer);
			return new ResponseDTO(aDTO, 200, "OK");

		}
		return new ResponseDTO(null, 500, "Error");
	}

	@PutMapping(path = "/updateExercise", produces = "application/json;charset=UTF-8")
	public String updataExercise(@RequestBody Exercises newExercise, HttpSession session) {
		Users uSession = (Users) session.getAttribute("logState");
		if (uSession != null) {
			System.err.println("Session" + uSession.getUsersId());
		}
		Users user = new Users();
		user.setUsersId(uSession.getUsersId());
		newExercise.setUsers(user);
		Exercises result = eService.insertNewExercise(newExercise);

		if (result != null) {
			return "OK";
		}
		return "Error";
	}

	@DeleteMapping(path = "/deleteExercise/{eId}")
	public String deleteExercise(@PathVariable Integer eId) {
		System.out.println(eId);

		return eService.deleteExercise(eId);
	}

	@DeleteMapping(path = "/deleteExercisePermissions/{epId}")
	public String deleteExercisePermissions(@PathVariable Integer epId) {
		System.err.println(epId);
		return eService.deleteExercisePermission(epId);
	}

	@DeleteMapping("/deleteQuestion/{qId}")
	public String deleteQuestion(@PathVariable Integer qId) {
		Question q = qService.findById(qId);
		q.setDelete(true);
		qService.insertNewQuestion(q);
		return "OK";
	}

	@DeleteMapping("/deleteAnswer/{aId}")
	public String deleteAnswer(@PathVariable Integer aId) {
		Answer a = aService.findById(aId);
		a.setDelete(true);
		aService.insertNewAnswer(a);
		return "OK";
	}

}
