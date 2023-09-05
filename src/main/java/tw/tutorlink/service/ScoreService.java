package tw.tutorlink.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.Comment;
import tw.tutorlink.bean.Lessons;
import tw.tutorlink.repository.LessonsDAO;
import tw.tutorlink.repository.ScoreDAO;

@Service
public class ScoreService {

	@Autowired
	private ScoreDAO sDAO;
	
	@Autowired
	private LessonsDAO lDAO;

	// 新增
	public void insert(Comment sc) {
		sDAO.save(sc);
	}

	// ID查詢
	public Comment findById(Integer id) {
		Optional<Comment> optional = sDAO.findById(id);

		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}


	// ID刪除
	public void deleteById(Integer id) {
		sDAO.deleteById(id);
	}
	
	
	// 查詢全部
	public List<Comment> findAllCommentList() {
		List<Comment> scoreList = sDAO.findAllCommentList();
		return scoreList;
	}

	// 靠課程ID查詢資料
	public Lessons findLessonsById(Integer id){
		Optional<Lessons> lessons = lDAO.findById(id);
		if(lessons.isPresent()) {
			return lessons.get();
		}
		return null;
	}
}
