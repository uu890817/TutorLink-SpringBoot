package tw.tutorlink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.tutorlink.bean.OrderItem;

public interface OrderItemDAO extends JpaRepository<OrderItem, Integer> {

	@Query("FROM OrderItem o  WHERE o.users.usersId = :usersId AND o.orderStates=0")
	public List<OrderItem> findOrderByUserId(@Param("usersId") Integer usersId);

	@Query("FROM OrderItem o  WHERE o.users.usersId = :usersId AND o.orderStates=1")
	public List<OrderItem> findApplyRefundById(@Param("usersId") Integer usersId);

	@Query("FROM OrderItem o  WHERE o.users.usersId = :usersId AND o.orderStates=2")
	public List<OrderItem> findRefundById(@Param("usersId") Integer usersId);

	@Query("FROM OrderItem o  WHERE o.orderId=:orderId")
	public OrderItem findOrderByOrderId(@Param("orderId") Integer orderId);

	@Query("FROM OrderItem o  WHERE o.lesson.lessonType=false")
	public List<OrderItem> findVideoRevenue();

	@Query("FROM OrderItem o  WHERE o.lesson.lessonType=true")
	public List<OrderItem> findLessonRevenue();
	
	@Query("FROM OrderItem o  WHERE o.lesson.lessonType=false AND o.users.usersId = :usersId")
	public List<OrderItem> findTeacherVideoRevenue(@Param("usersId") Integer usersId);

	@Query("FROM OrderItem o  WHERE o.lesson.lessonType=true AND o.users.usersId = :usersId")
	public List<OrderItem> findTeacherLessonRevenue(@Param("usersId") Integer usersId);
	
	@Query("FROM OrderItem o  WHERE o.lesson.lessonType=false AND o.lesson.subject.subjectId = :subjectId")
	public List<OrderItem> findVideoRevenueBySubject(@Param("subjectId") Integer subjectId);
	
	@Query("FROM OrderItem o  WHERE o.lesson.lessonType=true AND o.lesson.subject.subjectId = :subjectId")
	public List<OrderItem> findLessonRevenueBySubject(@Param("subjectId") Integer subjectId);
}
