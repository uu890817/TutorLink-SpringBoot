package tw.tutorlink.controller;

import java.util.Date;
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
import tw.tutorlink.bean.Lessons;
import tw.tutorlink.bean.Report;
import tw.tutorlink.bean.Users;
import tw.tutorlink.service.ReportService;

@RestController
public class ReportController {

	@Autowired
	private ReportService rService;

	// 查詢所有檢舉
	@GetMapping("/report")
	@ResponseBody
	public List<Report> findAllCommentList() {
		List<Report> fScore = rService.findAllReportList();
		return fScore;
	}

	// 查詢使用者檢舉
	@GetMapping("/report/selectbyuser")
	@ResponseBody
	public List<Report> findReportListByUsersId(@RequestParam("uid") Integer uid) {
		if (uid != null) {
			List<Report> fScore = rService.findReportListByUsersId(uid);
			return fScore;
		} else {
			return null;
		}
	}

	// 查詢課程所有檢舉
	@GetMapping("/report/selectbylesson")
	@ResponseBody
	public List<Report> findReportListByLessonId(@RequestParam("lid") Integer lid) {
		if (lid != null) {
			List<Report> fScore = rService.findReportListByLessonId(lid);
			return fScore;
		} else {
			return null;
		}
	}

	// 新增檢舉 /report?lid= 課程id
	@PostMapping(path = "/report", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String InsertReport(@RequestBody Report sc, HttpSession session,@RequestParam("lid") Integer lid) {
		Users loggedInUser = (Users) session.getAttribute("logState");
		Users user = rService.findUserId(loggedInUser.getUsersId());
		Lessons lesson = rService.findLessonsById(lid);
		sc.setStatus(0);
		sc.setUsers(user);
		sc.setLesson(lesson);
		rService.insert(sc);
		return "新增成功";
	}

	// 刪除單筆檢舉
	@DeleteMapping("/report/delete")
	@ResponseBody
	public String deleteReport(@RequestParam("id") Integer id) {
		Report report = rService.findById(id);
		if (report == null) {
			return "沒有這筆資料";
		}
		rService.deleteById(id);
		return "刪除成功";
	}

	// 修改單筆檢舉狀態
	@PutMapping("/report/update")
	@ResponseBody
	public String updateById(@RequestParam("rId") Integer rid, @RequestBody Report newReport) {
		Report report = rService.findById(rid);
		if (report != null) {
			if (newReport.getStatus() >= 0 && newReport.getStatus() <= 2) {
				report.setStatus(newReport.getStatus());
				report.setProcessingDate(new Date());
				rService.insert(report);
				return "修改狀態成功";
			}
			return "狀態碼錯誤";
		}
		return "沒有這筆資料";
	}
}
