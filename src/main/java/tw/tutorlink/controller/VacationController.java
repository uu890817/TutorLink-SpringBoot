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
import tw.tutorlink.bean.Users;
import tw.tutorlink.bean.Vacation;
import tw.tutorlink.service.VacationService;

@RestController
public class VacationController {
	@Autowired
	private VacationService vService;

	// 透過使用者查詢行事曆
	@GetMapping("/vacation")
	@ResponseBody
	public List<Vacation> findVacationByUserId(HttpSession session) {
		Users loggedInUser = (Users) session.getAttribute("logState");
		Integer id = loggedInUser.getUsersId();
		if (id != null) {
			List<Vacation> usersVacation = vService.findVacationByUsersId(id);
			return usersVacation;
		} else {
			return null;
		}
	}

	// 新增假期
	@PostMapping("/vacation")
	@ResponseBody
	public String InsertVacation(@RequestBody Vacation vc, HttpSession session) {
		Users loggedInUser = (Users) session.getAttribute("logState");
		Integer id = loggedInUser.getUsersId();
		Users user = vService.findUserId(id);
		vc.setUsers(user);
		vService.insert(vc);
		return "新增成功";
	}

	// 修改課程付款狀態
	@PutMapping("/vacation/update")
	@ResponseBody
	public String updateById(@RequestParam("vid") Integer vid, @RequestBody Vacation vc) {
		Vacation vacation = vService.findById(vid);
		if (vacation != null) {
			vacation.setVacationTime(vc.getVacationTime());
			vacation.setRepeatDayOfWeek(vc.getRepeatDayOfWeek());
			vacation.setRepeat(vc.getRepeat());
			vService.insert(vacation);
			return "修改狀態成功";
		}
		return "沒有這筆資料";
	}

	// 刪除單筆評論
	@DeleteMapping("/vacation/delete")
	@ResponseBody
	public String deleteReport(@RequestParam("id") Integer id) {
		Vacation vacation = vService.findById(id);
		if (vacation == null) {
			return "沒有這筆資料";
		}
		vService.deleteById(id);
		return "刪除成功";
	}

}
