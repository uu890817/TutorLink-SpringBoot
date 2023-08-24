package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.CourseQA;

public interface CourseQADAO extends JpaRepository<CourseQA, Integer> {

}
