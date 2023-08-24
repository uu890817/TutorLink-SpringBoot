package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.Question;

public interface QuestionsDAO extends JpaRepository<Question, Integer> {

}
