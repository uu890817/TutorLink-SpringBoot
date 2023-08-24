package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.Score;

public interface ScoreDAO extends JpaRepository<Score, Integer> {

}
