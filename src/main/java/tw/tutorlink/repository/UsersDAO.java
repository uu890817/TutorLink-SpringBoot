package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.tutorlink.bean.Users;

public interface UsersDAO extends JpaRepository<Users, Integer> {

	@Query("from Users where googleSubId = :sub")
	// 這邊的where 後面搜尋的欄位 要輸入Bean裡面給的屬性
	// EX: private String googleSubId;
	public Users findByGoogleSubId(@Param("sub") String googleSubId);
	
	@Query("from Users where usersId = :id")
	public Users findById(@Param("id") int id);
	
	@Query("from Users u join u.userDetail where usersId = :id ")
	public Users findByIdDetail(@Param("id") int id);

}
