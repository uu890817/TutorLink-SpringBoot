package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.Exercises;

public interface ExercisesDAO extends JpaRepository<Exercises, Integer> {

}
