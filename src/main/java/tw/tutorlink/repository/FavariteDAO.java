package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.Favorite;

public interface FavariteDAO extends JpaRepository<Favorite, Integer> {

}
