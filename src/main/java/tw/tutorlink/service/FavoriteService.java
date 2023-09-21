package tw.tutorlink.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.Favorite;
import tw.tutorlink.bean.Lessons;
import tw.tutorlink.bean.Users;
import tw.tutorlink.dto.lessontool.FavoriteDTO;
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
		List<Favorite> favoriteList = fDAO.findFavoriteListByUsersId(usersId);
		return favoriteList;
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
	
	// 透過查詢收藏
	public List<FavoriteDTO> findByUsersId(Integer usersId) {
	    List<Favorite> favoriteList = fDAO.findFavoriteListByUsersId(usersId);
	    List<FavoriteDTO> favoriteDtoList = new ArrayList<>();
	    for (Favorite favorite : favoriteList) {
	    	Lessons lessons = favorite.getLesson();	   
	    	// 圖片轉址
	    	String savePath = "c:/temp/upload/image/";
			String imagePath = savePath+lessons.getImage();
			try {
				byte[] fileBytes = readFileToByteArray(imagePath);
		        String base64Image = Base64.getEncoder().encodeToString(fileBytes);
		        lessons.setImage(base64Image);
			}catch(IOException e){
				e.printStackTrace();	
			}
	        Users teacher = findUserId(lessons.getUsers().getUsersId());
	        FavoriteDTO lDTO = new FavoriteDTO(lessons, teacher, favorite);
	        favoriteDtoList.add(lDTO);
	    }

	    return favoriteDtoList;
	}
	
	// 新增收藏用
	public FavoriteDTO createFavoriteAndReturnDTO(Users user, Lessons lesson,Favorite favorite) {
	    Lessons lessons = findLessonsById(lesson.getLessonId()); 
	    Users teacher = findUserId(lessons.getUsers().getUsersId());
	    FavoriteDTO lDTO = new FavoriteDTO(lessons, teacher, favorite);
	    return lDTO;
	}
	
	// 圖片轉址
	private byte[] readFileToByteArray(String filePath) throws IOException {
	    File file = new File(filePath);
	    FileInputStream fis = new FileInputStream(file);
	    byte[] fileBytes = new byte[(int) file.length()];
	    fis.read(fileBytes);
	    fis.close();
	    return fileBytes;
	}
}
