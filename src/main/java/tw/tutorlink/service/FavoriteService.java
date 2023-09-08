package tw.tutorlink.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.Favorite;
import tw.tutorlink.bean.Lessons;
import tw.tutorlink.bean.Users;
import tw.tutorlink.repository.FavoriteDAO;
import tw.tutorlink.repository.LessonsDAO;
import tw.tutorlink.repository.UsersDAO;

@Service
public class FavoriteService {
	
	@Autowired
	private FavoriteDAO fDAO;
	
	@Autowired
	private LessonsDAO lDAO;
	
	@Autowired
	private UsersDAO uDao;
	
	public Favorite findById(Integer id) {
		Optional<Favorite> optional = fDAO.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	// 新增收藏
	public void insert(Favorite fv) {
		fDAO.save(fv);
	}
	
	// 使用者ID查詢
	public List<Favorite> findFavoriteListByUsersId(Integer usersId) {
		List<Favorite> scoreList = fDAO.findFavoriteListByUsersId(usersId);
		return scoreList;
	}
	
	// ID刪除
	public void deleteById(Integer id) {
		fDAO.deleteById(id);
	}
	
	// 課程ID查詢課程
	public Lessons findLessonsById(Integer id){
		Optional<Lessons> lessons = lDAO.findById(id);
		if(lessons.isPresent()) {
			return lessons.get();
		}
		return null;
	}
	
	// 使用者ID查詢使用者
	public Users findUserId(Integer uID) {
		return uDao.findById(uID).get();
	}
}
