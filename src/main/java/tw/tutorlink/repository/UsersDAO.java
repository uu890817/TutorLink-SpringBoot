package tw.tutorlink.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Query("from Users where userEmail = :mail")
	public Users findByMail(@Param("mail") String mail);

	@Query("from Users where userPassword = :pwd and userEmail = :mail" )
	public Users findByPwd(@Param("pwd") String pwd ,@Param("mail") String mail);
	
	@Query("from Users u join u.applyTeacher where usersId = :id ")
	public Users checkId(int id);
	
	//查詢該資料表總筆數
	public long count ();
	
	// 分頁使用方法
	public Page<Users> findAll(Pageable pageable);
}
