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
import tw.tutorlink.bean.ExercisePermissions;
import tw.tutorlink.bean.StudentAnswers;
import tw.tutorlink.bean.Topics;
import tw.tutorlink.bean.Users;
import tw.tutorlink.dto.exercises.ResponseDTO;
import tw.tutorlink.dto.exercises.StudentGetExerciseDTO;
import tw.tutorlink.service.ExercisePermissionsService;
import tw.tutorlink.service.ExercisesService;
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
	public StudentGetExerciseDTO getExercise(@PathVariable Integer epId) {
		return epService.studentGetExerciseByExerId(epId);
	}

	@PostMapping("/sendExercise")
	public boolean sendExercise(@RequestBody List<StudentAnswers> sAns, HttpSession session) {
		Double eachScore = (double) (Math.round(100 / sAns.size() * 100) / 100);
		System.out.println(eachScore);
		Integer Finalscore =  100;
		Integer wrongTopic = 0;
		Users uSession = (Users) session.getAttribute("logState");
		Users u = new Users();
		u.setUsersId(uSession.getUsersId());
		boolean isErr = false;
		for (StudentAnswers ans : sAns) {
			List<String> answer = tService.getAnswer(ans.getTopics().getTopicsId());
			String[] splitAnswer = ans.getAnswer().split("<AND>");
			boolean hasWrong = false;
			for (String ansStr : answer) {
				System.out.println("ans:" + ansStr);
				for (int i = 0; i < splitAnswer.length; i++) {
					if (!ansStr.equals(splitAnswer[i])) {
						hasWrong = true;
					}
					System.err.println("正確: " + ansStr + "錯誤: " + splitAnswer[i] + " " + hasWrong);
				}
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
		ExercisePermissions epResult = epService.getExercisePermissionsByepId(sAns.get(0).getExercisePermissions().getExerPerId());
		epResult.setScore(Finalscore);
		epService.shareExercise(epResult);
		
		
		System.err.println("錯誤數:" + wrongTopic);
		System.err.println("總分:" + Finalscore);
		return isErr;
	}

}
