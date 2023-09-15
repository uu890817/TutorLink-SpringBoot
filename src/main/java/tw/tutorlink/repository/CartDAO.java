package tw.tutorlink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.tutorlink.bean.CartItem;

public interface CartDAO extends JpaRepository<CartItem, Integer> {

	@Query("FROM CartItem c  WHERE c.users.usersId = :usersId AND c.status=0")
	public List<CartItem> findByUsers(@Param("usersId") Integer usersId);

	@Query("FROM CartItem c  WHERE c.cartId = :cartId")
	public CartItem findBycId(@Param("cartId") Integer cartId);

	@Query("FROM CartItem c  WHERE c.status = :status AND c.users.usersId = :usersId")
	public List<CartItem> findShoppingCart(@Param("usersId") Integer usersId, @Param("status") Integer status);
	
	@Query("FROM CartItem c  WHERE c.users.usersId = :usersId AND c.status=2")
	public List<CartItem> findOrder(@Param("usersId") Integer usersId);
	
	@Query("UPDATE CartItem c SET c.status = 2 WHERE c.users.usersId = :usersId")
	public boolean deleteCartByUserId(@Param("usersId") Integer usersId);
	
}
