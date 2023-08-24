package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.Answer;

public interface AnswerDAO extends JpaRepository<Answer, Integer> {

}
