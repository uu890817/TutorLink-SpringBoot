package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.Users;

public interface UsersDAO extends JpaRepository<Users, Integer> {

}
