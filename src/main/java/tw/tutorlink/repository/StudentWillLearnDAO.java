package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.StudentWillLearn;

public interface StudentWillLearnDAO extends JpaRepository<StudentWillLearn, Integer> {

}
