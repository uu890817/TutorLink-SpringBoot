package tw.tutorlink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tw.tutorlink.bean.Comment;
import tw.tutorlink.bean.Lessons;
import tw.tutorlink.bean.Users;
import tw.tutorlink.dto.lessontool.CommentDTO;
import tw.tutorlink.service.ScoreService;

@RestController
public class ScoreController {
	
	@Autowired
	private ScoreService sService;
	
	// 查詢所有評論
	@GetMapping("/comment")
	@ResponseBody
	public List<CommentDTO> findAllCommentList(){
		List<CommentDTO> fScore = sService.findAllComment();
		return fScore;
	}
	
	// 查詢使用者所有評論
	@GetMapping("/comment/selectbyuser")
	@ResponseBody
	public List<Comment> findCommentListByUsersId(@RequestParam("uid") Integer uid){
		if(uid!=null) {
			List<Comment> fScore = sService.findCommentListByUsersId(uid);
			return fScore;
		} else {
			return null;
		}
	}
	
	// 查詢課程所有評論
	@GetMapping("/comment/selectbylesson")
	@ResponseBody
	public List<Comment> findCommentListByLessonId(@RequestParam("lid") Integer lid){
		if(lid!=null) {
			List<Comment> fScore = sService.findCommentListByLessonId(lid);
			return fScore;
		} else {
			return null;
		}
	}
	
	// 新增單一評論
	@PostMapping(path="/comment",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String InsertScore(@RequestBody Comment sc,HttpSession session,@RequestParam("lid") Integer lid) {
		Users loggedInUser = (Users) session.getAttribute("logState");
		Users user = sService.findUserId(loggedInUser.getUsersId());
		Lessons lesson = sService.findLessonsById(lid);
		sc.setUsers(user);
		sc.setLesson(lesson);
		sService.insert(sc);
		return "新增成功";
	}
	
	// 刪除單筆評論
	@DeleteMapping("/comment/delete")
	@ResponseBody
	public String deleteScore(@RequestParam("id") Integer id) {
		Comment comment = sService.findById(id);
		if(comment==null) {
			return "沒有這筆資料";
		}
		sService.deleteById(id);
		return "刪除成功";
	}
	
	
	// 修改單筆評論
	@PutMapping("/comment/update")
	@ResponseBody
	public String updateById(@RequestParam("comId") Integer cid,@RequestBody Comment newComment) {
		Comment comment = sService.findById(cid);
		if(comment!=null) {
	        comment.setRateTags(newComment.getRateTags());
	        comment.setRate(newComment.getRate());
	        comment.setRateContent(newComment.getRateContent());
	        sService.insert(comment);
			return "修改成功";
		}
		return "沒有這筆資料";
	}
}
