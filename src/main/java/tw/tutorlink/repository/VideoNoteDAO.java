package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.VideoNote;

public interface VideoNoteDAO extends JpaRepository<VideoNote, Integer> {

}
