package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.LessonPost;

public interface LessonPostDAO extends JpaRepository<LessonPost, Integer> {

}
