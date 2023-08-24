package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.Report;

public interface ReportDAO extends JpaRepository<Report, Integer> {

}
