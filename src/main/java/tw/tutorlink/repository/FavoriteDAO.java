package tw.tutorlink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.tutorlink.bean.Favorite;

public interface FavoriteDAO extends JpaRepository<Favorite, Integer> {
	@Query("SELECT f FROM Users u JOIN u.favorite f WHERE u.usersId = :usersId")
	List<Favorite> findFavoriteListByUsersId(@Param("usersId") Integer usersId);
}
