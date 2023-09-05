package tw.tutorlink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import tw.tutorlink.bean.Comment;
import tw.tutorlink.service.ScoreService;

@RestController
public class ScoreController {
	
	@Autowired
	private ScoreService sService;
	
	// 查詢所有評論
	@GetMapping("/favoriate")
	public List<Comment> findAllCommentList(){
		System.out.println("HI CON");
		List<Comment> fScore = sService.findAllCommentList();
		return fScore;
	}
}
