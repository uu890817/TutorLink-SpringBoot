package tw.tutorlink.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.tutorlink.bean.ApplyTeacher;

public interface ApplyTeacherDAO extends JpaRepository<ApplyTeacher,Integer> {

	
	//使用分頁查詢
	Page<ApplyTeacher> findAll(Pageable pageable);

	@Query("from ApplyTeacher  where applyTeacherId = :id ")
	public ApplyTeacher findByAtId(@Param("id") int  applyid);
	
	

	

}
