package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.Options;

public interface OptionDAO extends JpaRepository<Options, Integer> {

}
