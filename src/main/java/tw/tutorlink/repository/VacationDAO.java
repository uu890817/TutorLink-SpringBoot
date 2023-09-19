package tw.tutorlink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.tutorlink.bean.Vacation;

public interface VacationDAO extends JpaRepository<Vacation, Integer> {
	@Query("SELECT v FROM Users u JOIN u.vacation v WHERE u.usersId = :usersId")
	List<Vacation> findVacationByUsersId(@Param("usersId") Integer usersId);
	
}
