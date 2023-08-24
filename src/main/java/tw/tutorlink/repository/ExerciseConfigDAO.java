package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.ExerciseConfig;

public interface ExerciseConfigDAO extends JpaRepository<ExerciseConfig, Integer> {

}
