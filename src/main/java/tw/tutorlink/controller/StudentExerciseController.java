package tw.tutorlink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tw.tutorlink.bean.Answer;
import tw.tutorlink.bean.ExercisePermissions;
import tw.tutorlink.bean.Question;
import tw.tutorlink.bean.StudentAnswers;
import tw.tutorlink.bean.Users;
import tw.tutorlink.dto.exercises.AnswerDTO;
import tw.tutorlink.dto.exercises.QuestionDTO;
import tw.tutorlink.dto.exercises.ResponseDTO;
import tw.tutorlink.dto.exercises.StudentGetExerciseDTO;
import tw.tutorlink.service.AnswerService;
import tw.tutorlink.service.ExercisePermissionsService;
import tw.tutorlink.service.ExercisesService;
import tw.tutorlink.service.QuestionsService;
import tw.tutorlink.service.StudentAnswersService;
import tw.tutorlink.service.TopicsService;

@RestController
@RequestMapping("/student")
public class StudentExerciseController {

	@Autowired
	ExercisePermissionsService epService;
	@Autowired
	ExercisesService eService;
	@Autowired
	StudentAnswersService saService;
	@Autowired
	TopicsService tService;
	@Autowired
	QuestionsService qService;
	@Autowired
	AnswerService aService;

	public Integer getUserId(HttpSession session) {
		try {

			Users user = (Users) session.getAttribute("logState");
			return user.getUsersId();
		} catch (Exception e) {
			System.err.println("Session無法取得");
			return null;
		}

	}

	@GetMapping("/test")
	public StudentGetExerciseDTO testApi() {
		return epService.studentGetExerciseByExerId(1);
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

	@GetMapping("/doExercise/{epId}")
	public ResponseDTO getExercise(@PathVariable Integer epId) {
		return new ResponseDTO(epService.studentGetExerciseByExerId(epId), 200, "OK");
	}

	@GetMapping("/getFinishExercise/{epId}")
	public ResponseDTO getFinishExercise(@PathVariable Integer epId, HttpSession session) {
		Users uSession = (Users) session.getAttribute("logState");
		if (uSession != null) {

		}
		return new ResponseDTO(epService.studentGetFinishExercise(epId, uSession.getUsersId()), 200, "OK");
	}

	@GetMapping("/getAllQuestion/{epId}")
	public ResponseDTO getAllQuestion(@PathVariable Integer epId, HttpSession session) {
		Integer userId = this.getUserId(session);
		if (userId != null) {
			ExercisePermissions ep = epService.getExercisePermissionsByepId(epId);
			Integer exerId = ep.getExercises().getExerId();
			List<QuestionDTO> allQuestion = qService.getAllQuestion(exerId, userId);
			ResponseDTO responseDTO = new ResponseDTO(allQuestion, 200, "OK");

			return responseDTO;
		}
		return new ResponseDTO(null, 500, "Error");
	}

	@PostMapping("/sendExercise")
	public String sendExercise(@RequestBody List<StudentAnswers> sAns, HttpSession session) {
		Double eachScore = (double) (Math.round(100 / sAns.size() * 100) / 100);
		System.out.println(eachScore);
		Integer Finalscore = 100;
		Integer wrongTopic = 0;
		Users uSession = (Users) session.getAttribute("logState");
		Users u = new Users();
		u.setUsersId(uSession.getUsersId());
		boolean isErr = false;
		for (StudentAnswers ans : sAns) {
			List<String> answer = tService.getAnswer(ans.getTopics().getTopicsId());
			String[] splitAnswer = ans.getAnswer().split("<AND>");
			boolean hasWrong = false;
			int correctCount = 0;
			for (String ansStr : answer) {
				System.err.println("ans:" + ansStr);
				for (int i = 0; i < splitAnswer.length; i++) {
					if (!ansStr.equals(splitAnswer[i])) {
						hasWrong = true;
					} else {
						correctCount++;
					}
					System.err.println("正確: " + ansStr + "錯誤: " + splitAnswer[i] + " " + hasWrong);
				}
			}
			System.err.println("correctCount" + correctCount);
			System.err.println("answer.size()" + answer.size());
			if (correctCount == answer.size()) {
				hasWrong = false;
			}
			if (splitAnswer.length > answer.size()) {
				hasWrong = true;
			}
			if (hasWrong) {
				wrongTopic++;
			}

			ans.setUsers(u);
			String result = saService.insertStudentAns(ans);
			if (!result.equals("OK")) {
				isErr = true;
			}
		}

		Finalscore = (int) (Finalscore - (wrongTopic * eachScore));
		if (sAns.size() == wrongTopic) {
			Finalscore = 0;
		}
		ExercisePermissions epResult = epService
				.getExercisePermissionsByepId(sAns.get(0).getExercisePermissions().getExerPerId());
		epResult.setScore(Finalscore);
		epService.shareExercise(epResult);

		System.err.println("錯誤數:" + wrongTopic);
		System.err.println("總分:" + Finalscore);
		return isErr ? "Error" : "OK";
	}

	@PostMapping("/addNewQuestion/{epId}")
	public ResponseDTO addNewQuestion(@RequestBody Question que, @PathVariable Integer epId, HttpSession session) {
		Integer userId = this.getUserId(session);
		if (userId != null) {
			Users u = new Users();
			ExercisePermissions ep = epService.getExercisePermissionsByepId(epId);

			u.setUsersId(userId);
			que.setUsers(u);
			que.setExercises(ep.getExercises());

			return new ResponseDTO(new QuestionDTO(qService.insertNewQuestion(que)), 200, "OK");
		}
		return new ResponseDTO(null, 500, "Error");
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

}
