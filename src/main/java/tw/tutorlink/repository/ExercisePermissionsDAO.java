package tw.tutorlink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.tutorlink.bean.ExercisePermissions;
import tw.tutorlink.dto.exercises.StudentGetAllExerciseDTO;

public interface ExercisePermissionsDAO extends JpaRepository<ExercisePermissions, Integer> {


	@Query("FROM ExercisePermissions ep WHERE ep.users.usersId = :uId")
	public List<ExercisePermissions> findExercisePermissionsByuId(@Param("uId") Integer uId);
	
	@Query("FROM ExercisePermissions ep WHERE ep.exerPerId = :epId")
	public ExercisePermissions findExercisePermissionsByepId(@Param("epId") Integer epId);

	@Query("FROM ExercisePermissions ep WHERE ep.exerPerId = :epId AND ep.users.usersId = :uId")
	public ExercisePermissions findFinishExercisePermissions(@Param("epId") Integer epId, @Param("uId") Integer uId);
	
	
}
