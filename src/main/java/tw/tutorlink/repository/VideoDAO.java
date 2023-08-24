package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.Video;

public interface VideoDAO extends JpaRepository<Video, Integer> {

}
