
package tw.tutorlink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.tutorlink.bean.Subject;

public interface SubjectDAO extends JpaRepository<Subject, Integer> {

	@Query("from Subject where subjectContent like %:subjectContent% ")
	List<Subject> findBySubjectContentLike(@Param("subjectContent")String subjectContent);
}
