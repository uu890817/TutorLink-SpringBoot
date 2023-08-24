package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.Calender;

public interface CalenderDAO extends JpaRepository<Calender, Integer> {

}
