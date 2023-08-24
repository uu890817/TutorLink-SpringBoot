package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.Topics;

public interface TopicsDAO extends JpaRepository<Topics, Integer> {

}
