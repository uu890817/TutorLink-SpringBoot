package tw.tutorlink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.ApplyTeacher;
import tw.tutorlink.bean.Users;
import tw.tutorlink.repository.ApplyTeacherDAO;
import tw.tutorlink.repository.UsersDAO;

@Service
public class ApplyTeacherService {
	@Autowired
	private UsersDAO uDAO;

	@Autowired
	private ApplyTeacherDAO atDAO;

	public String applyTeacher(int id, String name, String idNumber, String country, String lessons, String langs,
			String exp, String jobstate, String hours, String salary, String advantage) {

		Users result = uDAO.findById(id);
		if (result != null) {
			ApplyTeacher at = new ApplyTeacher();
			at.setUsers(result);
			at.setIdNumber(idNumber);
			at.setExp(exp);
			at.setCountry(country);
			at.setMainlessons(lessons);
			at.setHours(hours);
			at.setJobstate(jobstate);
			at.setLangs(langs);
			at.setSalary(salary);
			at.setAdvantage(advantage);
			at.setState("已申請");
			result.getUserDetail().setTeacherState(2);
			atDAO.save(at);
			uDAO.save(result);
			return "ok";
		}

		return null;
	}

	public String checkstate(int id) {
		Users result = uDAO.checkId(id);
		if (result != null) {
			return "已申請";
		}
		return null;
	}

	public List<ApplyTeacher> findAllApply(int page, int rows) {
		if (page == 0 && rows == 0) {
			return atDAO.findAll();
		}
		
		Pageable pageable = PageRequest.of(page, rows);
		Page<ApplyTeacher> result = atDAO.findAll(pageable);
		return result.getContent();
	}

	public long count() {
		return atDAO.count();
	}
}
