package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.Vacation;

public interface VacationDAO extends JpaRepository<Vacation, Integer> {

}
