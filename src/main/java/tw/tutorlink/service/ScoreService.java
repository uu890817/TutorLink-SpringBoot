package tw.tutorlink.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.Comment;
import tw.tutorlink.repository.ScoreDAO;

@Service
public class ScoreService {

	@Autowired
	private ScoreDAO sDAO;
	
		// 新增
		public void insert(Comment sc) {
			sDAO.save(sc);
		}
		
		// ID查詢
		public Comment findById(Integer id) {
			Optional<Comment> optional = sDAO.findById(id);
		
			if(optional.isPresent()) {
				return optional.get();
			}
			return null;
		}
		public List<Comment> findAll() {
			List<Comment> scoreList = sDAO.findAll();
			return scoreList;
		}
		// ID刪除
		public void deleteById(Integer id) {
			sDAO.deleteById(id);
		}
		
		public List<Comment> findAllCommentList() {
			List<Comment> scoreList = sDAO.findAllCommentList();
			return scoreList;
		}

}
