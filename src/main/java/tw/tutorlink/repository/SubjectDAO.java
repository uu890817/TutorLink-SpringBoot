package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.Subject;

public interface SubjectDAO extends JpaRepository<Subject, Integer> {

}
