package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.repository.VideoDAO;

@Service
public class VideoService {
	
	@Autowired
	private VideoDAO vDAO;

}
