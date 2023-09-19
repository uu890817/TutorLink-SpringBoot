package tw.tutorlink.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.Users;
import tw.tutorlink.bean.Vacation;
import tw.tutorlink.repository.UsersDAO;
import tw.tutorlink.repository.VacationDAO;

@Service
public class VacationService {

	@Autowired
	private VacationDAO vDAO;
	
	@Autowired
	private UsersDAO uDao;
	
		// ID查詢
		public Vacation findById(Integer id) {
			Optional<Vacation> optional = vDAO.findById(id);
			if (optional.isPresent()) {
				return optional.get();
			}
			return null;
		}

		// 新增
		public void insert(Vacation vc) {
			vDAO.save(vc);
		}
		
		

		// 使用者ID查詢
		public List<Vacation> findVacationByUsersId(Integer usersId) {
			List<Vacation> vacation = vDAO.findVacationByUsersId(usersId);
			return vacation;
		}


		// ID刪除
		public void deleteById(Integer id) {
			vDAO.deleteById(id);
		}

		
		// 使用者ID查詢使用者
		public Users findUserId(Integer uID) {
			return uDao.findById(uID).get();
		}
}
