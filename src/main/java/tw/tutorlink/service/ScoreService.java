package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.repository.ScoreDAO;

@Service
public class ScoreService {

	@Autowired
	private ScoreDAO sDAO;
}
