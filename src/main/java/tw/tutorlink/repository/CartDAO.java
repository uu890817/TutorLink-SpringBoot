package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.CartItem;

public interface CartDAO extends JpaRepository<CartItem, Integer> {

}
