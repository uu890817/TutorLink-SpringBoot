package tw.tutorlink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tw.tutorlink.bean.Favorite;
import tw.tutorlink.bean.Lessons;
import tw.tutorlink.bean.Users;
import tw.tutorlink.dto.lessontool.FavoriteDTO;
import tw.tutorlink.service.FavoriteService;

@RestController
public class FavoriteController {

	@Autowired
	private FavoriteService fService;


	// 查詢使用者所有收藏
	@GetMapping("/favorite")
	@ResponseBody
	public List<FavoriteDTO> findFavoriteListByUsersId(@RequestParam("uid") Integer uid) {
		if (uid != null) {
			List<FavoriteDTO> fScore = fService.findByUsersId(uid);
			return fScore;
		} else {
			return null;
		}
	}


	// 新增收藏
	@PostMapping("/favorite")
	@ResponseBody
	public FavoriteDTO InsertFavorite(@RequestParam("lid") Integer lid,@RequestParam("uid") Integer uid,@RequestBody Favorite fv) {
		Users user = fService.findUserId(uid);
		Lessons lesson = fService.findLessonsById(lid);
		fv.setUsers(user);
		fv.setLesson(lesson);
		fService.insert(fv);
		FavoriteDTO fDto = fService.createFavoriteAndReturnDTO(user, lesson,fv);
		return fDto;
	}

	// 刪除收藏
	@DeleteMapping("/favorite")
	@ResponseBody
	public String deleteFavorite(@RequestParam("id") Integer id) {
		Favorite favorite = fService.findById(id);
		if (favorite == null) {
			return "沒有這筆資料";
		}
		fService.deleteById(id);
		return "刪除成功";
	}



}
