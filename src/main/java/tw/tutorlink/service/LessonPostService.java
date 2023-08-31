package tw.tutorlink.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.LessonPost;
import tw.tutorlink.repository.LessonPostDAO;

@Service
public class LessonPostService {

	@Autowired
	private LessonPostDAO lpDAO;
	
	@Autowired
	public LessonPostService(LessonPostDAO lpDAO) {
		this.lpDAO = lpDAO;
	}
	
	//新增一筆公告
	public LessonPost createLessonPost(LessonPost lessonPost) {
		return lpDAO.save(lessonPost);
	}
	
	//查詢此課程公告
	public List<LessonPost> findLessonPost(Integer lessionDetailId){
		return lpDAO.findLessonPostByLessonDetailId(lessionDetailId);
	}
	
	//查詢此發佈者的公告
	public List<LessonPost> findLessPostByUsers(Integer usersId){
		return lpDAO.findLessonPostByUsersId(usersId);
	}
	
	// 修改特定公告
    public LessonPost updateLessonPost(Integer lessonPostId, String title, String postContent) {
        Optional<LessonPost> existingLessonPostOptional = lpDAO.findById(lessonPostId);

        if (existingLessonPostOptional.isPresent()) {
            LessonPost existingLessonPost = existingLessonPostOptional.get();
            existingLessonPost.setTitle(title);
            existingLessonPost.setPostContent(postContent);
            return lpDAO.save(existingLessonPost);
        }

        return null; // 或者抛出一个異常，表示公告不存在
    }
    
    //刪除一筆公告
    public void deleteLessonPost(Integer lessonPostId) {
    	if (lpDAO.existsById(lessonPostId)) {
    		lpDAO.deleteById(lessonPostId);;
    	}
    }
	
}
