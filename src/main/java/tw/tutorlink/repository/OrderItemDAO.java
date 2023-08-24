package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.OrderItem;

public interface OrderItemDAO extends JpaRepository<OrderItem, Integer> {

}
