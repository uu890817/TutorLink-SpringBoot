package tw.tutorlink.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.Options;
import tw.tutorlink.bean.Topics;
import tw.tutorlink.repository.TopicsDAO;

@Service
public class TopicsService {

	@Autowired
	private TopicsDAO tDAO;

	public List<Topics> getTopicsByExerciseId(Integer eId) {
		return tDAO.findByExerciseId(eId);
	}

	public List<String> getAnswer(Integer tId) {
		Topics t = tDAO.findBytId(tId);
		List<Options> options = t.getOptions();
		List<String> answers = new ArrayList<>();
		for(Options option: options) {
			if(option.getAnswer().equals("true")) {
				answers.add(option.getContent());
			}
			else if(option.getAnswer().equals("false")) {
			}
			else {
				answers.add(option.getAnswer());
			}

		}
		return answers;
		
	}

}
