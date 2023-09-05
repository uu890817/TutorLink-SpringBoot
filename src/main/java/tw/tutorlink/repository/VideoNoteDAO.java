package tw.tutorlink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.tutorlink.bean.VideoNote;


public interface VideoNoteDAO extends JpaRepository<VideoNote, Integer> {
	
//	List<VideoNote> findByVideoAndUsers(Video video, Users users);
	
//	List<VideoNote> findByVideo_VideoIdAndUsers_UsersId(Integer videoId,Integer usersid);
	
//	@Query("SELECT vn FROM VideoNote vn WHERE vn.video.videoId = :videoId AND vn.users.usersId = :usersId")
//	List<VideoNote> findVideoNoteByVideoIdAndUsersIdJhql(@Param("videoId") Integer videoId, @Param("usersId") Integer usersId);
	
	@Query("SELECT vn FROM VideoNote vn WHERE vn.video.id = :videoId AND vn.users.id = :usersId")
	List<VideoNote> findVideoNoteByVideoIdAndUsersIdJpql(@Param("videoId") Integer videoId, @Param("usersId") Integer usersId);
	
	

}

