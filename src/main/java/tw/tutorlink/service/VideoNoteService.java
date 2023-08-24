package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.repository.VideoNoteDAO;

@Service
public class VideoNoteService {

	@Autowired
	private VideoNoteDAO vdDAO;
}
