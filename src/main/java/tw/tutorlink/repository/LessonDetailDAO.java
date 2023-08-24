package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.LessonDetail;

public interface LessonDetailDAO extends JpaRepository<LessonDetail, Integer> {

}
