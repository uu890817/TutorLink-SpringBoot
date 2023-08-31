package tw.tutorlink.repository;


import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tw.tutorlink.bean.Calender;

public interface CalenderDAO extends JpaRepository<Calender, Integer> {

	@Query("FROM Users u JOIN u.calender WHERE u.usersId = ?1")
	Optional<Calender> findById(Integer usersId);
}
