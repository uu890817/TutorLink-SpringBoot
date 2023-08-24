package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.repository.FavariteDAO;

@Service
public class FavoriteService {
	
	@Autowired
	private FavariteDAO fDAO;
}
