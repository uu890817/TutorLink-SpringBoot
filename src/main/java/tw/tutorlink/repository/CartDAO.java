package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.Cart;

public interface CartDAO extends JpaRepository<Cart, Integer> {

}
