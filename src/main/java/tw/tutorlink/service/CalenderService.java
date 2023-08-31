package tw.tutorlink.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;



import tw.tutorlink.bean.Calender;
import tw.tutorlink.repository.CalenderDAO;

@Service
public class CalenderService {

	@Autowired
	private CalenderDAO cDAO;
	
	public Optional<Calender> findById(Integer id) {
		Optional<Calender> list = cDAO.findById(id);
		return list;
	}

}
