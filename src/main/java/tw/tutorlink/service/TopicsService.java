package tw.tutorlink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.Topics;
import tw.tutorlink.repository.TopicsDAO;

@Service
public class TopicsService {
	
	@Autowired
	private TopicsDAO tDAO;
	
	
	public List<Topics> getTopicsByExerciseId(Integer eId){
		return tDAO.findByExerciseId(1);
	}
	
	

}
