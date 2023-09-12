package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
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
			at.setState("審核中");
			atDAO.save(at);
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
}
