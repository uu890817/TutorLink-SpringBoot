package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.StudentAnswers;

public interface StudentAnswersDAO extends JpaRepository<StudentAnswers, Integer> {

}
