package tw.tutorlink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tw.tutorlink.bean.Comment;
import tw.tutorlink.bean.Lessons;
import tw.tutorlink.bean.Users;
import tw.tutorlink.service.ScoreService;

@RestController
public class ScoreController {
	
	@Autowired
	private ScoreService sService;
	
	// 查詢所有評論
	@GetMapping("/comment")
	public List<Comment> findAllCommentList(){
		List<Comment> fScore = sService.findAllCommentList();
		return fScore;
	}
	
	// 新增單一評論
	@PostMapping("/comment/{lid}")
	public String InsertScore(@PathVariable("lid") Integer lid, Comment sc,	HttpSession session) {
		// 取得登入的session
		Users user = (Users) session.getAttribute("logState");
		// int id = user.getUsersId();
		// 取得lesson
		Lessons lesson = sService.findLessonsById(lid);
		sc.setUsers(user);
		sc.setLesson(lesson);
		sService.insert(sc);
		return "新增成功";
	}
}
