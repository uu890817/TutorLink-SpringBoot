package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.UserDetail;

public interface UserDetailDAO extends JpaRepository<UserDetail, Integer> {

}
