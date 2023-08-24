package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.ExercisePermissions;

public interface ExercisePermissionsDAO extends JpaRepository<ExercisePermissions, Integer> {

}
