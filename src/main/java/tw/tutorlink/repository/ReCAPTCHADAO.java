package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.ReCAPTCHA;

public interface ReCAPTCHADAO extends JpaRepository<ReCAPTCHA,Integer> {

}
