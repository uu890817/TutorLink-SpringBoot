
package tw.tutorlink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import tw.tutorlink.bean.Subject;

public interface SubjectDAO extends JpaRepository<Subject, Integer> {

<<<<<<< HEAD
//	List<Subject> findBySubjectNameContaining(String SubjectContent);
=======
//	List<Subject> findBySubjectContentContaining(String SubjectContent);
>>>>>>> branch 'develop' of https://github.com/uu890817/TutorLink-SpringBoot.git
}
