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

import tw.tutorlink.bean.Calender;
import tw.tutorlink.bean.Lessons;
import tw.tutorlink.bean.Users;
import tw.tutorlink.dto.lessontool.CalenderDTO;
import tw.tutorlink.dto.lessontool.LessonsDTO;
import tw.tutorlink.service.CalenderService;

@RestController
public class CalenderController {
	@Autowired
	private CalenderService cService;

	
	// 透過使用者查詢行事曆
	@GetMapping("/calender/findbyuser")
	@ResponseBody
	public List<Calender> findCalenderByUserId(@RequestParam("uid") Integer id) {
		if (id != null) {
			List<Calender> usersCalender = cService.findCalenderListByUsersId(id);
			return usersCalender;
		} else {
			return null;
		}
	}
	
	// 透過使用者(老師)查詢所有課程行事曆
	@GetMapping("/calender/findbylesson")
	@ResponseBody
	public List<CalenderDTO> findCalenderByLessonId(@RequestParam("uid") Integer id) {
		if (id != null) {
			List<CalenderDTO> lessonCalender = cService.findCalenderbyUsersAllLessons(id);
			return lessonCalender;
		} else {
			return null;
		}
	}
	
	// 新增課程行事曆
	@PostMapping("/calender")
	@ResponseBody
	public String InsertReport(@RequestParam("uid") Integer uid,@RequestParam("lid") Integer lid,@RequestBody Calender cd) {
		Users user = cService.findUserId(uid);
		Lessons lesson = cService.findLessonsById(lid);
		cd.setUsers(user);
		cd.setLesson(lesson);
		cService.insert(cd);
		return "新增成功";
	}
	
	// 修改課程付款狀態
	@PutMapping("/calender/update")
	@ResponseBody
	public String updateById(@RequestParam("cid") Integer cid, @RequestParam("type") Integer type) {
		Calender calender = cService.findById(cid);
		if (calender != null) {
			if (type >= 0 && type <= 2) {
				calender.setLessonType(type);
				cService.insert(calender);
				return "修改狀態成功";
			}
			return "狀態碼錯誤";
		}
		return "沒有這筆資料";
	}
	
	// 刪除單筆評論
	@DeleteMapping("/calender/delete")
	@ResponseBody
	public String deleteReport(@RequestParam("id") Integer id) {
		Calender calender = cService.findById(id);
		if (calender == null) {
			return "沒有這筆資料";
		}
		cService.deleteById(id);
		return "刪除成功";
	}
	
	
	// 透過使用者(學生)查詢行事曆
	@GetMapping("/calender")
	@ResponseBody
	public List<LessonsDTO> findCalenderWithLessonsByUsersId(@RequestParam("uid") Integer id) {
		if (id != null) {
			List<LessonsDTO> l = cService.findLessonsByUsersId(id);
			return l;
		} else {
			return null;
		}
	}
}
