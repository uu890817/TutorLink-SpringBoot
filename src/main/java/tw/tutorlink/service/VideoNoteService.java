package tw.tutorlink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.VideoNote;
import tw.tutorlink.repository.VideoNoteDAO;

@Service
public class VideoNoteService {

	@Autowired
	private VideoNoteDAO vdDAO;
	
	@Autowired
	public VideoNoteService(VideoNoteDAO vdDAO) {
		this.vdDAO = vdDAO;
	}
	
	// 新增影片筆記
    public VideoNote createVideoNote(VideoNote videoNote) {
        return vdDAO.save(videoNote);
    }
    
    // 查詢此學生所有筆記
    public VideoNote getVideoNoteById(Integer id) {
        return vdDAO.findById(id).orElse(null);
    }

    
    //查詢此使用者在這部影片的筆記
    public List<VideoNote> findByLessonAndUser(Integer videoId,Integer usersId) {
        return vdDAO.findVideoNoteByVideoIdAndUsersIdJpql(videoId, usersId);
    }

    // 修改影片筆記
    public VideoNote updateVideoNote(Integer videoNoteId, String newNoteContent, Integer newTimeLine) {
        VideoNote existingVideoNote = vdDAO.findById(videoNoteId).orElse(null);

        if (existingVideoNote != null) {
            existingVideoNote.setNoteContent(newNoteContent);
            existingVideoNote.setTimeLine(newTimeLine);
            return vdDAO.save(existingVideoNote);
        }
        
        return null; // 或者抛出一个異常，表示筆記不存在
    }

    // 删除影片筆記
    public void deleteVideoNote(Integer id) {
        if (vdDAO.existsById(id)) {
            vdDAO.deleteById(id);
        }
    }
}
