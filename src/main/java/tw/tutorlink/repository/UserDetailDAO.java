package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.tutorlink.bean.UserDetail;

public interface UserDetailDAO extends JpaRepository<UserDetail, Integer> {
	
	@Query("SELECT ud.userName FROM UserDetail ud WHERE ud.users.usersId = :userId")
    String findUserNameByUserId(@Param("userId") Integer userId);
	
	UserDetail findByUsers_usersId(Integer userId);

}
